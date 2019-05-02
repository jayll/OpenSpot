//
//  ReservationsOptionCell.swift
//  OpenSpot
//
//  Created by Stephen Fung on 4/14/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import UIKit

class ReservationsOptionCell: UITableViewCell{
    @IBOutlet weak var addressCell: UILabel!
    @IBOutlet weak var priceLabel: UILabel!
    @IBOutlet weak var timeLabel: UILabel!
    @IBOutlet weak var starsLabel: UILabel!
    @IBOutlet weak var phoneNumber: UILabel!
    @IBAction func callButton(_ sender: Any) {
        if let url = URL(string: "tel://\(phoneNumber.text ?? "+16464724896")") {
            UIApplication.shared.open(url, options: [:], completionHandler: nil)
        }
    }
        
}
