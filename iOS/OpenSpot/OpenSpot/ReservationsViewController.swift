//
//  SecondViewController.swift
//  OpenSpot
//
//  Created by Stephen Fung and Jay Lliguichushca on 2/15/19.
//  Copyright © 2019 Stephen Fung. All rights reserved.
//

import UIKit
import Firebase

class ReservationsViewController: UIViewController {
    @IBOutlet weak var reservationsTableView: UITableView!
    var reservationsArray:[String] = []
    lazy var db = Firestore.firestore()
    lazy var currentUser = Auth.auth().currentUser
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func viewDidAppear(_ animated: Bool){
        // Do any additional setup after loading the view, typically from a nib.
        getReservations()
    }
    
    func getReservations(){
        db.collection("Users").document((currentUser?.uid)!).getDocument {(value, Error) in
            self.reservationsArray = value!["Reservations"] as! [String]
            self.reservationsTableView.reloadData()
        }
    }
    
}

extension ReservationsViewController: UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return reservationsArray.count / 5
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as! ReservationsOptionCell
        
        cell.addressCell.text = reservationsArray[indexPath.row * 6]
        cell.priceLabel.text = reservationsArray[indexPath.row * 6 + 1]
        cell.timeLabel.text = reservationsArray[indexPath.row * 6 + 2] + "  " + reservationsArray[indexPath.row * 6 + 3]
        cell.starsLabel.text = reservationsArray[indexPath.row * 6 + 4] + " ★"
        cell.phoneNumber.text = reservationsArray[indexPath.row * 6 + 5]
        
        return cell
    }
}
