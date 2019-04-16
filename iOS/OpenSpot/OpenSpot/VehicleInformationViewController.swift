//
//  VehicleInformationViewController.swift
//  OpenSpot
//
//  Created by Stephen Fung on 3/9/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import UIKit
import Firebase

class VehicleInformationViewController: UIViewController, UITextFieldDelegate, UIPickerViewDataSource, UIPickerViewDelegate, UIAlertViewDelegate {
    
    @IBOutlet weak var state: UITextField!
    @IBOutlet weak var license: UITextField!
    @IBOutlet weak var make: UITextField!
    @IBOutlet weak var model: UITextField!
    @IBOutlet weak var color: UITextField!
    @IBOutlet weak var skipButton: UIButton!
    
    
    var fullName = String?("")
    var email = String?("")
    var dateOfBirth = String?("")
    
    var passCarIndex = 0
    var cameFromVehicleMenu = false
    
    let statesArr = ["Alaska", "Alabama", "Arkansas", "American Samoa", "Arizona", "California", "Colorado", "Connecticut","District of Columbia","Delaware", "Florida", "Georgia", "Guam","Hawaii", "Iowa", "Idaho", "Illinois", "Indiana", "Kansas", "Kentucky", "Louisiana","Massachusetts", "Maryland", "Maine", "Michigan", "Minnesota", "Missouri", "Mississippi", "Montana", "North Carolina","North Dakota", "Nebraska", "New Hampshire", "New Jersey", "New Mexico", "Nevada", "New York", "Ohio", "Oklahoma","Oregon", "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas","Utah","Virginia", "Virgin Islands","Vermont","Washington","Wisconsin","West Virginia","Wyoming"]
    
    var makeArr = ["Acura", "Alfa Romeo", "Aston Martin", "Audi",  "Bentley", "BMW", "Buick", "Cadillac", "Chevrolet", "Chrysler", "Dodge","Ferrari", "FIAT","Ford","Freightliner","Genesis","GMC","Honda", "Hyundai","INFINITI","Jaguar","Jeep","Kia","Lamborghini","Land Rover","Lexus","Lincoln","Lotus", "Maserati","MAZDA","McLaren","Mercedes-Benz","MINI","Mitsubishi","Nissan","Porsche","Ram","Rolls-Royce","smart","Subaru","Tesla","Toyota","Volkswagen","Volvo"]
    
    
    var colorsArr = ["Black","Blue","Brown","Gold","Green","Red","Silver", "White", "Yellow"]
    
    var carDict: [String: [String]] = ["Acura": ["ILX", "MDX", "MDX Sport Hybrid", "NSX", "RDX", "RLX", "RLX Sport Hybrid", "TLX"],
                                       "Alfa Romeo": ["4C", "4C Spider", "Giulia", "Stelvio"],
                                       "Aston Martin": ["DB11", "Vanquish S"],
                                       "Audi": ["A3", "A3 Sportback e-tron", "A4", "A4 allroad", "A5", "A6", "A7", "A8", "e-tron", "Q3", "Q5", "Q7", "Q8", "R8", "RS 3", "RS 5", "RS 7", "S3", "S4", "S5", "S6", "S7", "S8", "SQ5", "TT"],
                                       "Bentley": ["Bentayga", "Continental", "Flying Spur", "Mulsanne"],
                                       "BMW": ["2 Series", "3 Series", "4 Series", "5 Series", "6 Series", "7 Series", "i3", "i8", "M2", "M3", "M4", "M5", "M6", "X1", "X2", "X3", "X4", "X5", "X5 M", "X6", "X6 M"],
                                       "Buick": ["Cascada", "Enclave", "Encore", "Envision", "LaCrosse", "Regal Sportback", "Regal TourX"],
                                       "Cadillac": ["ATS", "ATS-V", "CT6", "CTS", "CTS-V", "Escalade", "Escalade ESV", "XT4", "XT5", "XTS"],
                                       "Chevrolet": ["Blazer", "Bolt EV", "Camaro", "City Express", "Colorado Crew Cab", "Colorado Extended Cab", "Corvette", "Cruze", "Equinox", "Express 2500 Cargo", "Express 2500 Passenger", "Express 3500 Cargo", "Express 3500 Passenger", "Impala", "Malibu", "Silverado 1500 Crew Cab", "Silverado 1500 Double Cab", "Silverado 1500 LD Double Cab", "Silverado 1500 Regular Cab", "Silverado 2500 HD Crew Cab", "Silverado 2500 HD Double Cab", "Silverado 2500 HD Regular Cab", "Silverado 3500 HD Crew Cab", "Silverado 3500 HD Double Cab", "Silverado 3500 HD Regular Cab", "Sonic", "Spark", "Suburban", "Tahoe", "Traverse", "Trax", "Volt"],
                                       "Chrysler": ["300", "Pacifica", "Pacifica Hybrid"],
                                       "Dodge": ["Challenger", "Charger", "Durango", "Grand Caravan Passenger", "Journey"],
                                       "Ferrari": ["488 GTB", "488 Spider", "812 Superfast", "California", "F12berlinetta", "GTC4Lusso", "Portofino"],
                                       "FIAT": ["124 Spider", "500", "500 Abarth", "500c", "500c Abarth", "500e", "500L", "500X"],
                                       "Ford": ["C-MAX Hybrid", "EcoSport", "Edge", "Escape", "Expedition", "Expedition MAX", "Explorer", "F150 Regular Cab", "F150 Super Cab", "F150 SuperCrew Cab", "F250 Super Duty Crew Cab", "F250 Super Duty Regular Cab", "F250 Super Duty Super Cab", "F350 Super Duty Crew Cab", "F350 Super Duty Regular Cab", "F350 Super Duty Super Cab", "F450 Super Duty Crew Cab", "Fiesta", "Flex", "Focus", "Fusion", "Fusion Energi", "Mustang", "Ranger SuperCab", "Ranger SuperCrew", "Taurus", "Transit 150 Van", "Transit 150 Wagon", "Transit 250 Van", "Transit 350 HD Van", "Transit 350 Van", "Transit 350 Wagon", "Transit Connect Cargo", "Transit Connect Passenger"],
                                       "Freightliner": ["Sprinter 2500 Cargo", "Sprinter 2500 Crew", "Sprinter 2500 Passenger", "Sprinter 3500 Cargo", "Sprinter 3500XD Cargo", "Sprinter WORKER Cargo"],
                                       "Genesis": ["G70", "G80", "G90"],
                                       "GMC": ["Acadia", "Canyon Crew Cab", "Canyon Extended Cab", "Savana 2500 Cargo", "Savana 2500 Passenger", "Savana 3500 Cargo", "Savana 3500 Passenger", "Sierra 1500 Crew Cab", "Sierra 1500 Double Cab", "Sierra 1500 Regular Cab", "Sierra 2500 HD Crew Cab", "Sierra 2500 HD Double Cab", "Sierra 2500 HD Regular Cab", "Sierra 3500 HD Crew Cab", "Sierra 3500 HD Double Cab", "Sierra 3500 HD Regular Cab", "Terrain", "Yukon", "Yukon XL"],
                                       "Honda": ["Accord", "Accord Hybrid", "Civic", "Civic Type R", "Clarity Electric", "Clarity Fuel Cell", "Clarity Plug-in Hybrid", "CR-V", "Fit", "HR-V", "Insight", "Odyssey", "Passport", "Pilot", "Ridgeline"],
                                       "Hyundai": ["Accent", "Elantra", "Elantra GT", "Ioniq Electric", "Ioniq Hybrid", "Ioniq Plug-in Hybrid", "Kona", "Kona Electric", "NEXO", "Santa Fe", "Santa Fe Sport", "Santa Fe XL", "Sonata", "Sonata Hybrid", "Sonata Plug-in Hybrid", "Tucson", "Veloster"],
                                       "INFINITI": ["Q50", "Q60", "Q70", "QX30", "QX50", "QX60", "QX80"],
                                       "Jaguar": ["E-PACE", "F-PACE", "F-TYPE", "I-PACE", "XE", "XF", "XJ"],
                                       "Jeep": ["Cherokee", "Compass", "Grand Cherokee", "Renegade", "Wrangler", "Wrangler Unlimited"],
                                       "Kia": ["Cadenza", "Forte", "Forte5", "K900", "Niro", "Niro Plug-in Hybrid", "Optima", "Optima Hybrid", "Optima Plug-in Hybrid", "Rio", "Sedona", "Sorento", "Soul", "Soul EV", "Sportage", "Stinger", "Telluride"],
                                       "Lamborghini": ["Aventador", "Huracan"],
                                       "Land Rover": ["Discovery", "Discovery Sport", "Range Rover", "Range Rover Evoque", "Range Rover Sport", "Range Rover Velar"],
                                       "Lexus": ["ES", "GS", "GX", "IS", "LC", "LS", "LX", "NX", "RC", "RX", "UX"],
                                       "Lincoln": ["Aviator", "Continental", "MKC", "MKT", "MKX", "MKZ", "Nautilus", "Navigator", "Navigator L"],
                                       "Lotus": ["Evora 400"],
                                       "Maserati": ["Ghibli", "GranTurismo", "Levante", "Quattroporte"],
                                       "MAZDA": ["CX-3", "CX-5", "CX-9", "MAZDA3", "MAZDA6", "MX-5 Miata", "MX-5 Miata RF"],
                                       "McLaren": ["570GT", "570S", "720S"],
                                       "Mercedes-Benz": ["A-Class", "C-Class", "CLA", "CLS", "E-Class", "G-Class", "GLA", "GLC", "GLC Coupe", "GLE", "GLS", "Mercedes-AMG C-Class", "Mercedes-AMG CLA", "Mercedes-AMG CLS", "Mercedes-AMG E-Class", "Mercedes-AMG G-Class", "Mercedes-AMG GLA", "Mercedes-AMG GLC", "Mercedes-AMG GLC Coupe", "Mercedes-AMG GLE", "Mercedes-AMG GLE Coupe", "Mercedes-AMG GLS", "Mercedes-AMG GT", "Mercedes-AMG S-Class", "Mercedes-AMG SL", "Mercedes-AMG SLC", "Mercedes-Maybach S-Class", "Metris Cargo", "Metris Passenger", "Metris WORKER Cargo", "Metris WORKER Passenger", "S-Class", "SL", "SLC", "Sprinter 2500 Cargo", "Sprinter 2500 Crew", "Sprinter 2500 Passenger", "Sprinter 3500 Cargo", "Sprinter 3500 XD Cargo", "Sprinter WORKER Cargo"],
                                       "MINI": ["Clubman", "Convertible", "Countryman", "Hardtop 2 Door", "Hardtop 4 Door"],
                                       "Mitsubishi": ["Eclipse Cross", "Mirage", "Mirage G4", "Outlander", "Outlander PHEV", "Outlander Sport"],
                                       "Nissan": ["370Z", "Altima", "Armada", "Frontier Crew Cab", "Frontier King Cab", "GT-R", "Kicks", "LEAF", "Maxima", "Murano", "NV1500 Cargo", "NV200", "NV2500 HD Cargo", "NV3500 HD Cargo", "NV3500 HD Passenger", "Pathfinder", "Rogue", "Rogue Sport", "Sentra", "Titan Crew Cab", "Titan King Cab", "TITAN Single Cab", "TITAN XD Crew Cab", "TITAN XD King Cab", "TITAN XD Single Cab", "Versa", "Versa Note"],
                                       "Porsche": ["718 Boxster", "718 Cayman", "911", "Cayenne", "Macan", "Panamera"],
                                       "Ram": ["1500 Classic Crew Cab", "1500 Classic Quad Cab", "1500 Classic Regular Cab", "1500 Crew Cab", "1500 Quad Cab", "1500 Regular Cab", "2500 Crew Cab", "2500 Mega Cab", "2500 Regular Cab", "3500 Crew Cab", "3500 Mega Cab", "3500 Regular Cab", "ProMaster Cargo Van", "ProMaster City", "ProMaster Window Van"],
                                       "Rolls-Royce": ["Dawn", "Ghost", "Phantom", "Wraith"],
                                       "smart": ["fortwo electric drive", "fortwo electric drive cabrio"],
                                       "Subaru": ["Ascent", "BRZ", "Crosstrek", "Forester", "Impreza", "Legacy", "Outback", "WRX"],
                                       "Tesla": ["Model 3", "Model S", "Model X", "Model Y", "Roadster"],
                                       "Toyota": ["4Runner", "86", "Avalon", "Avalon Hybrid", "Camry", "Camry Hybrid", "C-HR", "Corolla", "Corolla Hatchback", "Corolla iM", "Highlander", "Highlander Hybrid", "Land Cruiser", "Mirai", "Prius", "Prius c", "Prius Prime", "RAV4", "RAV4 Hybrid", "Sequoia", "Sienna", "Tacoma Access Cab", "Tacoma Double Cab", "Tundra CrewMax", "Tundra Double Cab", "Yaris", "Yaris iA"],
                                       "Volkswagen": ["Atlas", "Beetle", "e-Golf", "Golf", "Golf Alltrack", "Golf GTI", "Golf R", "Golf SportWagen", "Jetta", "Passat", "Tiguan", "Tiguan Limited"],
                                       "Volvo": ["S60", "S90", "V60", "V90", "XC40", "XC60", "XC90"]
    ]
    
    
    
    
    var statePicker = UIPickerView()
    var makePicker = UIPickerView()
    var modelPicker = UIPickerView()
    var colorsPicker = UIPickerView()
    
    var isSignedUpVar = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        isSignedUp()
        
        state.underlined()
        license.underlined()
        make.underlined()
        model.underlined()
        color.underlined()
        
        statePicker.delegate = self
        statePicker.dataSource = self
        state.inputView = statePicker
        
        makePicker.delegate = self
        makePicker.dataSource = self
        make.inputView = makePicker
        
        modelPicker.delegate = self
        modelPicker.dataSource = self
        model.inputView = modelPicker
        
        colorsPicker.delegate = self
        colorsPicker.dataSource = self
        color.inputView = colorsPicker
        
        license.delegate = self
        
 
    }
    
    func isSignedUp(){
        let db = Firestore.firestore()
        let currentUser = Auth.auth().currentUser
        db.collection("Users").document((currentUser?.uid)!).getDocument { (value, Error) in
            if value!["fullName"] != nil{
                self.isSignedUpVar = true
                self.skipButton.isHidden=true
                if(self.cameFromVehicleMenu){
                    let user = db.collection("Users").document((currentUser?.uid)!)
                    var carArray = [String]()
                    user.getDocument { (value, Error) in
                        carArray = (value!["Cars"] as? [String])!
                        self.make.text = carArray[self.passCarIndex]
                        self.model.text = carArray[self.passCarIndex+1]
                        self.color.text = carArray[self.passCarIndex+2]
                        self.state.text = carArray[self.passCarIndex+3]
                        self.license.text = carArray[self.passCarIndex+4]
                    }
                }
            }
        }
    }
    

    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        let maxLength = 7
        let currentString: NSString = license.text! as NSString
        let newString: NSString =
            currentString.replacingCharacters(in: range, with: string) as NSString
        return newString.length <= maxLength
    }
    
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if(pickerView == statePicker){
            return statesArr.count
        }else if (pickerView == makePicker){
            return makeArr.count
        }else if (pickerView == modelPicker){
            let modelArr = carDict[make.text!]
            return modelArr!.count
        }else if(pickerView == colorsPicker){
            return colorsArr.count
        }
        return 0
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if(pickerView == statePicker){
            state.text = statesArr[row]
        }else if (pickerView==makePicker){
            make.text = makeArr[row]
            if(make.text != ""){
                model.text = ""
                color.text = ""
                self.modelPicker.selectRow(0, inComponent: 0, animated: false)
                self.colorsPicker.selectRow(0, inComponent: 0, animated: false)
            }
        }else if (pickerView == modelPicker){
            let modelArr = carDict[make.text!]
            model.text = modelArr![row]
        }else if(pickerView == colorsPicker){
            color.text = colorsArr[row]
        }
        
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if(pickerView == statePicker){
            return statesArr[row]
        }else if (pickerView == makePicker){
            return makeArr[row]
        }else if (pickerView == modelPicker){
            let modelArr = carDict[make.text!]
            return modelArr![row]
        }else if(pickerView == colorsPicker){
            return colorsArr[row]
        }
        return ""
    }
    
    let db = Firestore.firestore()
    let currentUser = Auth.auth().currentUser
    
    @IBAction func skipClicked(_ sender: UIButton) {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let controller = storyboard.instantiateViewController(withIdentifier: "UITabBarController")
        self.present(controller, animated: true, completion: nil)
        
        db.collection("Users").document((currentUser?.uid)!).setData([
            "phoneNumber": currentUser?.phoneNumber! as Any,
            "fullName": fullName as Any,
            "email": email as Any,
            "dateOfBirth": dateOfBirth as Any,
            "Cars": []
            ])
    }
    
    @IBAction func finishClicked(_ sender: UIButton) {
        var check = false
        if(state.text=="" || make.text=="" || model.text=="" || color.text==""){
            let alertController = UIAlertController(title: "OpenSpot", message: "please fill out all information", preferredStyle: UIAlertController.Style.alert)
            alertController.addAction(UIAlertAction(title: "Ok", style: UIAlertAction.Style.default, handler: nil))
            self.present(alertController, animated: true, completion: nil)
        }else if((state.text != "" || make.text != "" || model.text != "" || color.text != "")&&((license.text?.count)!<=2)){
            let alertController1 = UIAlertController(title: "OpenSpot", message: "please enter a valid license plate number", preferredStyle: UIAlertController.Style.alert)
            alertController1.addAction(UIAlertAction(title: "Ok", style: UIAlertAction.Style.default, handler: nil))
            self.present(alertController1, animated: true, completion: nil)
        }else{
            check=true
        }
        
        if (check == true) {

            if (isSignedUpVar==false){
                db.collection("Users").document((currentUser?.uid)!).setData([
                    "phoneNumber": currentUser?.phoneNumber! as Any,
                    "fullName": fullName as Any,
                    "email": email as Any,
                    "dateOfBirth": dateOfBirth as Any,
                    "Cars":[make.text!,model.text!,color.text!,state.text!,license.text!]
                    ])
            }else{
                let user = db.collection("Users").document((currentUser?.uid)!)
                var carArray = [String]()
                if(self.cameFromVehicleMenu){
                    user.getDocument { (value, Error) in
                        carArray = (value!["Cars"] as? [String])!
                        if(self.make.text != carArray[self.passCarIndex]){
                            carArray[self.passCarIndex] = self.make.text!
                        }
                        if( self.model.text != carArray[self.passCarIndex+1]){
                            carArray[self.passCarIndex+1]=self.model.text!
                        }
                        if(self.color.text != carArray[self.passCarIndex+2]){
                            carArray[self.passCarIndex+2]=self.color.text!
                        }
                        if(self.state.text != carArray[self.passCarIndex+3]){
                            carArray[self.passCarIndex+3]=self.state.text!
                        }
                        if(self.license.text != carArray[self.passCarIndex+4]){
                            carArray[self.passCarIndex+4]=self.license.text!
                        }
                        user.updateData([
                            "Cars":carArray,
                            ])
                    }
                }else{
                    user.getDocument { (value, Error) in
                        carArray = (value!["Cars"] as? [String])!
                        carArray.append(self.make.text!)
                        carArray.append(self.model.text!)
                        carArray.append(self.color.text!)
                        carArray.append(self.state.text!)
                        carArray.append(self.license.text!)
                        user.updateData([
                            "Cars":carArray,
                            ])
                        
                    }
                    self.cameFromVehicleMenu = true
                }
                

            }
        }
        if(self.cameFromVehicleMenu){
            self.navigationController?.popViewController(animated: true)
        }else{
            self.navigationController?.dismiss(animated: true, completion:nil);

        }
        
    }
    
}
