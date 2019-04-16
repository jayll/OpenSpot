//
//  MenuOptions.swift
//  OpenSpot
//
//  Created by Stephen Fung and Jay Lliguichushca on 2/15/19.
//  Copyright © 2019 Stephen Fung. All rights reserved.
//

import UIKit

enum MenuOption: Int, CustomStringConvertible {
    
    case Person
    case Driveway
    case Payment
    case Vehicle
    case Support
    case signOut
    
    var description: String {
        switch self {
        case .Person: return "Loading name"
        case .Driveway: return "List your driveway"
        case .Payment: return "Payment"
        case .Vehicle: return "Vehicle"
        case .Support: return "Contact us"
        case .signOut: return "Log out"
        }
    }
    
    var image: UIImage {
        switch self {
        case .Person: return UIImage(named: "Person") ?? UIImage()
        case .Driveway: return UIImage(named: "Driveway") ?? UIImage()
        case .Payment: return UIImage(named: "Payment") ?? UIImage()
        case .Vehicle: return UIImage(named: "Vehicle") ?? UIImage()
        case .Support: return UIImage(named: "Support") ?? UIImage()
        case .signOut: return UIImage(named: "SignOut") ?? UIImage()
        }
    }
}
