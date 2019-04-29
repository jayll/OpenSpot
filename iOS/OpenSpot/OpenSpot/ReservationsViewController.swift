//
//  SecondViewController.swift
//  OpenSpot
//
//  Created by Stephen Fung and Jay Lliguichushca on 2/15/19.
//  Copyright © 2019 Stephen Fung. All rights reserved.
//

import UIKit

class ReservationsViewController: UIViewController {
    let address = ["This is all hard coded for this weeks demo", "South Campus", "Wegmans"]
    let reservationTime = ["1/3/19, 5:45pm" , "2/12/19, 2:13pm", "4/1/19, 9:20pm"]
    let prices = ["10", "7", "4"]
    let stars = ["5.0", "3.4","4.7"]
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    
}

extension ReservationsViewController: UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return address.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as! ReservationsOptionCell
        
        cell.addressCell.text = address[indexPath.row]
        cell.priceLabel.text = "$" + prices[indexPath.row] + "/hr"
        cell.timeLabel.text = reservationTime[indexPath.row]
        cell.starsLabel.text = stars[indexPath.row]
        
        return cell
    }
}
