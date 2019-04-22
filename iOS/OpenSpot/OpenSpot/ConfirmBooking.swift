//
//  ConfirmBooking.swift
//  OpenSpot
//
//  Created by Stephen Fung on 4/19/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//
import UIKit
import Firebase
import GoogleMaps
import MapKit

class ConfirmBooking: UIViewController, UIPickerViewDataSource{
    @IBOutlet weak var drivewayMapView: GMSMapView!
    @IBOutlet weak var drivewayLocationLabel: UILabel!
    @IBOutlet weak var priceLabel: UILabel!
    @IBOutlet weak var drivewayOwnerLabel: UILabel!
    @IBOutlet weak var callButton: UIButton!
    @IBOutlet weak var selectCarTextField: UITextField!
    @IBOutlet weak var bookDrivewayButton: UIButton!
    
    lazy var coord = CLLocationCoordinate2D(latitude: 43.0008, longitude: 78.7890)
    var locationName = String?("")
    var price = String?("")
    var drivewayOwnerName = String?("")
    var phoneNumber = String?("")
    var carsArray: [String] = []
    let db = Firestore.firestore()
    let currentUser = Auth.auth().currentUser
    var carPicker = UIPickerView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        selectCarTextField.underlined()
        getCarsArray()
        setUpView()
        
        carPicker.delegate = self
        carPicker.dataSource = self
        selectCarTextField.inputView = carPicker
    }
    
    func getCarsArray(){
        db.collection("Users").document((currentUser?.uid)!).getDocument { (value, Error) in
            var tempArray = value!["Cars"] as! [String]
            var index = 0
            while index < tempArray.count{
                self.carsArray += [tempArray[index] + " " + tempArray[index + 1] + " - " + tempArray[index + 4]]
                index += 5
            }
        }
    }
    
    func setUpView(){
        priceLabel.text = price
        drivewayLocationLabel.text = locationName
        let camera = GMSCameraPosition.camera(withLatitude: coord.latitude, longitude: coord.longitude, zoom: 16)
        drivewayMapView?.camera = camera
        
        let marker = GMSMarker(position: coord)
        marker.map = drivewayMapView
        drivewayMapView.settings.setAllGesturesEnabled(false)
        
        self.navigationItem.leftBarButtonItem = UIBarButtonItem(title: "Close", style: .done, target: self, action: #selector(dismissVC))
        callButton.addTarget(self, action: #selector(clickCallButton), for: .touchUpInside)
        bookDrivewayButton.addTarget(self, action: #selector(bookDriveway), for: .touchUpInside)
        
        drivewayOwnerLabel.text = drivewayOwnerName! + "'s driveway"
    }
    
    @objc func dismissVC() {
        self.dismiss(animated: true, completion: nil)
    }
    
    @objc func clickCallButton() {
        if let url = URL(string: "tel://\(phoneNumber!)") {
            UIApplication.shared.open(url, options: [:], completionHandler: nil)
        }
    }
    
    @objc func bookDriveway(){
        self.dismiss(animated: true, completion: nil)
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
}

extension ConfirmBooking: GMSMapViewDelegate{
    func mapView(_ mapView: GMSMapView, didTapAt coordinate: CLLocationCoordinate2D) {
        let source = MKMapItem(placemark: MKPlacemark(coordinate: coord))
        source.name = drivewayOwnerLabel.text
        
        MKMapItem.openMaps(with: [source], launchOptions: [MKLaunchOptionsDirectionsModeKey: MKLaunchOptionsDirectionsModeDriving])
    }
    
    func mapView(_ mapView: GMSMapView, didTap marker: GMSMarker) -> Bool {
        let source = MKMapItem(placemark: MKPlacemark(coordinate: coord))
        source.name = drivewayOwnerLabel.text
        
        MKMapItem.openMaps(with: [source], launchOptions: [MKLaunchOptionsDirectionsModeKey: MKLaunchOptionsDirectionsModeDriving])
        return true
    }
}

extension ConfirmBooking: UIPickerViewDelegate{
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return carsArray.count
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        selectCarTextField.text = carsArray[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return carsArray[row]
    }
}
