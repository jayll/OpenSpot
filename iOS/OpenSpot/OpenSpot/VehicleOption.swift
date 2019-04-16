//
//  VehicleOption.swift
//  OpenSpot
//
//  Created by Jay Lliguichushca on 3/31/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import UIKit

enum VehicleOption: Int, CustomStringConvertible {
    
    case Car
    
    var description: String {
        switch self {
        case .Car: return "Vehicle"
        }
    }
    
    var image: UIImage {
        switch self {
        case .Car: return UIImage(named: "vehicleFront") ?? UIImage()
        }
    }
}
