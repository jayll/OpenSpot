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

class ConfirmBooking: UIViewController{
    @IBOutlet weak var drivewayMapView: GMSMapView!
    @IBOutlet weak var drivewayLocationLabel: UILabel!
    @IBOutlet weak var priceLabel: UILabel!
    @IBOutlet weak var drivewayOwnerLabel: UILabel!
    @IBOutlet weak var callButton: UIButton!
    @IBOutlet weak var selectCarTextField: UITextField!
    @IBOutlet weak var bookDrivewayButton: UIButton!
    var price = String?("")
    var coord = CLLocationCoordinate2D(latitude: 43.0008, longitude: 78.7890)
    var locationName = String?("")
    
    override func viewDidLoad() {
        super.viewDidLoad()
        selectCarTextField.underlined()

        priceLabel.text = price
        drivewayLocationLabel.text = locationName
        let camera = GMSCameraPosition.camera(withLatitude: coord.latitude, longitude: coord.longitude, zoom: 16)
        drivewayMapView?.camera = camera
        
        let marker = GMSMarker(position: coord)
        marker.map = drivewayMapView
        drivewayMapView.settings.setAllGesturesEnabled(false)
        
        self.navigationItem.leftBarButtonItem = UIBarButtonItem(title: "Close", style: .done, target: self, action: #selector(dismissVC))
        callButton.addTarget(self, action: #selector(clickCallButton), for: .touchUpInside)
        
    }
    
    @objc func dismissVC() {
        self.dismiss(animated: true, completion: nil)
    }
    
    @objc func clickCallButton() {
        if let url = URL(string: "tel://\("+16464724896")") {
            UIApplication.shared.open(url, options: [:], completionHandler: nil)
        }
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
