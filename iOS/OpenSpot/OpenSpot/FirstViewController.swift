//
//  FirstViewController.swift
//  OpenSpot
//
//  Created by Stephen Fung and Jay Lliguichushca on 2/15/19.
//  Copyright © 2019 Stephen Fung. All rights reserved.
//

import UIKit
import FirebaseUI
import CoreLocation
import GoogleMaps
import GooglePlaces
import Firebase

class FirstViewController: UIViewController, FUIAuthDelegate {
    var locationManager = CLLocationManager()
    var mapView: GMSMapView!
    var placesClient: GMSPlacesClient!
    var zoomLevel: Float = 16.0
    var searchLocationMarker: GMSMarker?
    
    var resultsViewController: GMSAutocompleteResultsViewController?
    var searchController: UISearchController?
    lazy var db = Firestore.firestore()
    lazy var currentUser = Auth.auth().currentUser
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpLocationManager()
        setUpMap()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        ThirdViewController.isLoggedOut = false
        
        if Auth.auth().currentUser != nil {
            db.collection("Users").document((currentUser?.uid)!).getDocument{ (document, error) in
                if let document = document, !document.exists {
                    let storyboard = UIStoryboard(name: "Main", bundle: nil)
                    let controller = storyboard.instantiateViewController(withIdentifier: "NavigationController")
                    self.present(controller, animated: false, completion: nil)
                }
            }
        }

        self.getAvailableDriveways()
    }
    
    func setUpLocationManager(){
        locationManager = CLLocationManager()
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.requestAlwaysAuthorization()
        locationManager.distanceFilter = 100000
        locationManager.startUpdatingLocation()
        locationManager.delegate = self
    }
    
    func setUpMap(){
        placesClient = GMSPlacesClient.shared()
        
        // Create a map.
        let camera = GMSCameraPosition.camera(withLatitude: 43.0008, longitude: 78.7890, zoom: zoomLevel)
        mapView = GMSMapView.map(withFrame: view.bounds, camera: camera)
        mapView.settings.myLocationButton = true
        mapView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        mapView.isMyLocationEnabled = true
        mapView.delegate = self
        
        // Add the map to the view, hide it until we've got a location update.
        view.addSubview(mapView)
        mapView.settings.tiltGestures = false
        mapView.settings.rotateGestures = false
        mapView.isHidden = true
        
        resultsViewController = GMSAutocompleteResultsViewController()
        resultsViewController?.delegate = self
        
        searchController = UISearchController(searchResultsController: resultsViewController)
        searchController?.searchResultsUpdater = resultsViewController
        
        // Put the search bar in the navigation bar.
        searchController?.searchBar.sizeToFit()
        navigationItem.titleView = searchController?.searchBar
        
//        var navcontroller = UINavigationBar.self
//        navigationController.
//        var navigationBarAppearace = UINavigationBar.appearance()
//        navigationBarAppearace.tintColor = UIColor(red: 1, green: 1, blue: 1, alpha: 1)
//        navigationBarAppearace.barTintColor = UIColor(red: 1, green: 1, blue: 1, alpha: 1)
//        searchController.tint
        
        // When UISearchController presents the results view, present it in
        // this view controller, not one further up the chain.
        definesPresentationContext = true
        
        // Prevent the navigation bar from being hidden when searching.
        searchController?.hidesNavigationBarDuringPresentation = false
    }
    
    func getAvailableDriveways(){
        db.collection("Users").getDocuments { (value, error) in
            for account in value!.documents{
                let address = account.data()["Addresses"] as? [String]
                if address != nil && address!.count > 0{
                    var index = 0
                    while index != address!.count{
                        if address![index + 4] == "1"{
                            let drivewayMarker = CustomGMSMarker(position: CLLocationCoordinate2D(latitude: Double(address![index + 1])!, longitude: Double(address![index + 2])!))
                            let price = "$" + address![index + 3] + ".00"
                            drivewayMarker.iconView = CustomMarkerView(frame: CGRect(x: 0, y: 0, width: 50 , height: 50), image: #imageLiteral(resourceName: "Border"), price: price)
                            drivewayMarker.snippet = price + "/hr"
                            drivewayMarker.title = address![index]
                            drivewayMarker.tracksViewChanges = false
                            drivewayMarker.map = self.mapView
                            drivewayMarker.phoneNumber = account.data()["phoneNumber"] as! String
                            drivewayMarker.drivewayOwnerName = account.data()["fullName"] as! String
                        }
                        index += 5
                    }
                }
            }
            
        }
    }
}

extension FirstViewController: GMSMapViewDelegate{
    func mapView(_ mapView: GMSMapView, didTapInfoWindowOf marker: GMSMarker) {
        print(marker.title!)
        let VC = self.storyboard!.instantiateViewController(withIdentifier: "ConfirmBooking") as! ConfirmBooking
        VC.price = marker.snippet!
        VC.coord = marker.position
        VC.locationName = marker.title
        let a = marker as! CustomGMSMarker
        VC.phoneNumber = a.phoneNumber
        VC.drivewayOwnerName = a.drivewayOwnerName
        let navController = UINavigationController(rootViewController: VC) // Creating a navigation controller with VC1 at the root of the navigation stack.
        self.present(navController, animated: true, completion: nil)
    }
}

extension FirstViewController: CLLocationManagerDelegate {
    // Handle incoming location events.
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        let location: CLLocation = locations.last!
        print("Location: \(location)")
        let camera = GMSCameraPosition.camera(withLatitude: location.coordinate.latitude, longitude: location.coordinate.longitude, zoom: zoomLevel)
        if mapView.isHidden {
            mapView.isHidden = false
            mapView.camera = camera
        } else {
            mapView.animate(to: camera)
        }
    }
    
    // Handle authorization for the location manager.
    func locationManager(_ manager: CLLocationManager, didChangeAuthorization status: CLAuthorizationStatus) {
        switch status {
        case .restricted:
            print("Location access was restricted.")
        case .denied:
            print("User denied access to location.")
            // Display the map using the default location.
            mapView.isHidden = false
        case .notDetermined:
            print("Location status not determined.")
        case .authorizedAlways: fallthrough
        case .authorizedWhenInUse:
            print("Location status is OK.")
        @unknown default:
            fatalError()
        }
    }
    
    // Handle location manager errors.
    func locationManager(_ manager: CLLocationManager, didFailWithError error: Error) {
        locationManager.stopUpdatingLocation()
        print("Error: \(error)")
    }
}

extension FirstViewController: GMSAutocompleteResultsViewControllerDelegate {
    func resultsController(_ resultsController: GMSAutocompleteResultsViewController, didAutocompleteWith place: GMSPlace) {
        let filter = GMSAutocompleteFilter()
        filter.country = Locale.current.regionCode
        resultsController.autocompleteFilter = filter
        searchController?.isActive = false
        // Do something with the selected place.
        
        let camera = GMSCameraPosition.camera(withLatitude: place.coordinate.latitude, longitude: place.coordinate.longitude, zoom: zoomLevel)
        self.mapView.animate(to: camera)
        
        searchLocationMarker?.map = nil
        searchLocationMarker = GMSMarker(position: CLLocationCoordinate2D(latitude: place.coordinate.latitude, longitude: place.coordinate.longitude))
        searchLocationMarker?.title = place.name
        searchLocationMarker?.map = self.mapView
        searchController?.searchBar.text = place.name


        
        print("Place name: \(place.name!)")
        print("Place lat: \(place.coordinate.latitude)")
        print("Place long: \(place.coordinate.longitude)")
        //        print("Place address: \(place.formattedAddress)")
        //        print("Place attributions: \(place.attributions)")
    }
    
    func resultsController(_ resultsController: GMSAutocompleteResultsViewController, didFailAutocompleteWithError error: Error){
        // TODO: handle the error.
        print("Error: ", error.localizedDescription)
    }
    
    // Turn the network activity indicator on and off again.
    func didRequestAutocompletePredictions(forResultsController resultsController: GMSAutocompleteResultsViewController) {
        UIApplication.shared.isNetworkActivityIndicatorVisible = true
    }
    
    func didUpdateAutocompletePredictions(forResultsController resultsController: GMSAutocompleteResultsViewController) {
        UIApplication.shared.isNetworkActivityIndicatorVisible = false
    }
}
