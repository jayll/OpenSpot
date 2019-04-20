//
//  ConfirmBooking.swift
//  OpenSpot
//
//  Created by Stephen Fung on 4/19/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import UIKit
import Firebase
import MapKit

class ConfirmBooking: UIViewController{
    @IBOutlet weak var drivewayMapView: MKMapView!
    @IBOutlet weak var drivewayLocationLabel: UILabel!
    @IBOutlet weak var priceLabel: UILabel!
    @IBOutlet weak var drivewayOwnerLabel: UILabel!
    @IBOutlet weak var callButton: UIButton!
    @IBOutlet weak var selectCarTextField: UITextField!
    @IBOutlet weak var bookDrivewayButton: UIButton!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
}
