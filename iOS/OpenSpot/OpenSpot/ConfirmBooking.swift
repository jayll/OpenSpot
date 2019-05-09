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
    //    @IBOutlet weak var drivewayOwnerLabel: UILabel!
    //    @IBOutlet weak var callButton: UIButton!
    @IBOutlet weak var selectCarTextField: UITextField!
    @IBOutlet weak var bookDrivewayButton: UIButton!
    
    //default value that is overwritten by FirstViewControllerVC
    lazy var coord = CLLocationCoordinate2D(latitude: 43.0008, longitude: 78.7890)
    var locationName = String?("")
    var price = String?("")
    var documentID = String?("")
    var phoneNumber = String?("")
    var carsArray: [String] = ["-SELECT-"]
    let db = Firestore.firestore()
    let currentUser = Auth.auth().currentUser
    var carPicker = UIPickerView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        selectCarTextField.underlined(color: #colorLiteral(red: 0.6156862745, green: 0.6039215686, blue: 0.937254902, alpha: 1))
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
        //        callButton.addTarget(self, action: #selector(clickCallButton), for: .touchUpInside)
        bookDrivewayButton.addTarget(self, action: #selector(bookDriveway), for: .touchUpInside)
        
        //        drivewayOwnerLabel.text = drivewayOwnerName! + "'s driveway"
    }
    
    @objc func dismissVC() {
        self.dismiss(animated: true, completion: nil)
    }
    
    //    @objc func clickCallButton() {
    //        if let url = URL(string: "tel://\(phoneNumber!)") {
    //            UIApplication.shared.open(url, options: [:], completionHandler: nil)
    //        }
    //    }
    
    func getDate() -> String{
        let months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
        let date = Date()
        let calendar = Calendar.current
        let components = calendar.dateComponents([.month, .day,.year], from: date)
        let month = components.month
        let day = components.day
        let year = components.year
        print(month as Any)
        print(day as Any)
        return months[month!-1] + " " + String(day ?? 1) + ", " + String(year ?? 2019)
    }
    
    func getTime() -> String{
        let date = Date()
        let calendar = Calendar.current
        let components = calendar.dateComponents([.hour], from: date)
        var hour = components.hour
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "mm"
        let newDateString = dateFormatter.string(from: date)
        
        if(hour!>13){
            hour! = hour! - 12
            return String(hour ?? 12) + ":" + newDateString+"PM"
        }else{
            return String(hour ?? 12) + ":" + newDateString+"AM"
        }
    
    }
    
    @objc func bookDriveway(){
        //        var check = false
        if(selectCarTextField.text == "" || selectCarTextField.text == "-SELECT-"){
            let alertController = UIAlertController(title: "OpenSpot", message: "Please select a car", preferredStyle: UIAlertController.Style.alert)
            alertController.addAction(UIAlertAction(title: "Ok", style: UIAlertAction.Style.default, handler: nil))
            self.present(alertController, animated: true, completion: nil)
        }else{
            //            check=true

            let database1 = db.collection("Users")
            var updatedAddress1 = [String]()
            database1.document(self.documentID!).getDocument{(value,error) in
                var updatedAddress = (value!["Addresses"] as? [String])!
                var index = 0
                while index != updatedAddress.count{
                    if ((updatedAddress[index + 1] == String(self.coord.latitude)) && (updatedAddress[index + 2] == String(self.coord.longitude))){
                        updatedAddress[index + 3] = "0"
                        updatedAddress1 = updatedAddress
                        break
                    }
                    index += 5
                }
            
                database1.document(self.documentID!).updateData([
                    "Addresses": updatedAddress1,
                    ])
                let currentAddress = self.drivewayLocationLabel.text
                let getPrice = self.priceLabel.text
                let date = self.getDate()
                let time = self.getTime()
                
                let user = self.db.collection("Users").document((self.currentUser?.uid)!)
                var reservationsArray = [String]()
                user.getDocument { (value, Error) in
                    reservationsArray = (value!["Reservations"] as? [String])!
                    reservationsArray.append(currentAddress!)
                    reservationsArray.append(getPrice!)
                    reservationsArray.append(date)
                    reservationsArray.append(time)
                    reservationsArray.append("5.0")
                    reservationsArray.append(self.phoneNumber!)
                    user.updateData([
                        "Reservations":reservationsArray,
                        ])
                }
                
                
            }
            
            self.dismiss(animated: true, completion: nil)
        }
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
}

extension ConfirmBooking: GMSMapViewDelegate{
    func mapView(_ mapView: GMSMapView, didTapAt coordinate: CLLocationCoordinate2D) {
        let source = MKMapItem(placemark: MKPlacemark(coordinate: coord))
        source.name = locationName
        MKMapItem.openMaps(with: [source], launchOptions: [MKLaunchOptionsDirectionsModeKey: MKLaunchOptionsDirectionsModeDriving])
    }
    
    func mapView(_ mapView: GMSMapView, didTap marker: GMSMarker) -> Bool {
        let source = MKMapItem(placemark: MKPlacemark(coordinate: coord))
        source.name = locationName
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
