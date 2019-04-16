//
//  FirstViewController.swift
//  OpenSpot
//
//  Created by Stephen Fung and Jay Lliguichushca on 2/15/19.
//  Copyright © 2019 Stephen Fung. All rights reserved.
//

import UIKit
import FirebaseUI
import MapKit
import CoreLocation
import Firebase

class FirstViewController: UIViewController, FUIAuthDelegate {
    
    @IBOutlet weak var mapView: MKMapView!
    let locationManager = CLLocationManager()
    let regionInMeters: Double = 1000
    
    @IBAction func zoomButton(_ sender: Any) {
        centerViewOnUserLocation()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        ThirdViewController.isLoggedOut = false
        let db = Firestore.firestore()
        let currentUser = Auth.auth().currentUser
        
        if Auth.auth().currentUser != nil {
            db.collection("Users").document((currentUser?.uid)!).getDocument{ (document, error) in
                if let document = document, !document.exists {
                    let storyboard = UIStoryboard(name: "Main", bundle: nil)
                    let controller = storyboard.instantiateViewController(withIdentifier: "NavigationController")
                    self.present(controller, animated: false, completion: nil)
                }
                else{
                    self.checkLocationServices()
                }
            }
        }
        else {
            let authUI = FUIAuth.defaultAuthUI()
            //            authUI?.isSignInWithEmailHidden = true
            FUIAuth.defaultAuthUI()?.shouldHideCancelButton = true
            authUI?.delegate = self
            let providers: [FUIAuthProvider] = [FUIPhoneAuth(authUI:FUIAuth.defaultAuthUI()!)]
            authUI?.providers = providers
            let authViewController = OpenSpotFirebaseUI(authUI: authUI!)
            let navc = UINavigationController(rootViewController: authViewController)
            self.present(navc, animated: false, completion: nil)
        }
        
    }
    
    func setupLocationManager() {
        locationManager.delegate = self
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
    }
    
    
    func centerViewOnUserLocation() {
        if let location = locationManager.location?.coordinate {
            let region = MKCoordinateRegion.init(center: location, latitudinalMeters: regionInMeters, longitudinalMeters: regionInMeters)
            mapView.setRegion(region, animated: true)
        }
    }
    
    
    func checkLocationServices() {
        if CLLocationManager.locationServicesEnabled() {
            setupLocationManager()
            checkLocationAuthorization()
        } else {
            // Show alert letting the user know they have to turn this on.
        }
    }
    
    
    func checkLocationAuthorization() {
        switch CLLocationManager.authorizationStatus() {
        case .authorizedWhenInUse:
            mapView.showsUserLocation = true
            centerViewOnUserLocation()
            //            locationManager.startUpdatingLocation()
            break
        case .denied:
            // Show alert instructing them how to turn on permissions
            break
        case .notDetermined:
            locationManager.requestWhenInUseAuthorization()
        case .restricted:
            // Show an alert letting them know what's up
            break
        case .authorizedAlways:
            break
        default:
            break
        }
    }
}

extension FirstViewController: CLLocationManagerDelegate {
    func locationManager(_ manager: CLLocationManager, didChangeAuthorization status: CLAuthorizationStatus) {
        checkLocationAuthorization()
    }
}
