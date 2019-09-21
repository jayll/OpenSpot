//
//  MapFilterMenuOptions.swift
//  OpenSpot
//
//  Created by Stephen Fung on 9/21/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import UIKit

enum MapFilterMenuOptions: Int, CustomStringConvertible {
    
    case Price
    case Date
    case Distance

    
    var description: String {
        switch self {
        case .Price: return "Price"
        case .Date: return "Date"
        case .Distance: return "Distance"
        }
    }
    
    var image: UIImage {
        switch self {
        case .Price: return UIImage(named: "Price") ?? UIImage()
        case .Date: return UIImage(named: "Date") ?? UIImage()
        case .Distance: return UIImage(named: "Distance") ?? UIImage()
        }
    }
}
