//
//  VehicleViewController.swift
//  OpenSpot
//
//  Created by Jay Lliguichushca on 3/30/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import UIKit
import FirebaseUI
import Firebase

class VehicleViewController: UIViewController{
    @IBOutlet weak var vehicleTableView: UITableView!
    var numofCars = 0
    var carArray = [String]()
    var index = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        getCarArr()
        self.navigationItem.title = "Vehicles"
        navigationItem.leftBarButtonItem = UIBarButtonItem(title: "Close", style: .plain, target: self, action:  #selector(dismissViewController))
        navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .add, target: self, action: #selector(newVehicleViewController))

        vehicleTableView.tableFooterView = UIView()
        vehicleTableView.register(MenuOptionCell.self, forCellReuseIdentifier: "cell")
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        if let index = self.vehicleTableView.indexPathForSelectedRow{
            self.vehicleTableView.deselectRow(at: index, animated: true)
        }

    }
    
    
    override func viewDidAppear(_ animated: Bool) {
        getCarArr()
    }
    
    func getCarArr(){
        let db = Firestore.firestore()
        let currentUser = Auth.auth().currentUser
        db.collection("Users").document((currentUser?.uid)!).getDocument {(value, Error) in
            let getCarArray = value!["Cars"] as? [String]
                self.carArray = getCarArray!
            self.vehicleTableView.reloadData()
        }
    }
    
    @objc func dismissViewController(){
        dismiss(animated: true, completion: nil)
    }
    @objc func newVehicleViewController(){
        let destinationVC = self.storyboard?.instantiateViewController(withIdentifier: "VehicleInformationViewController") as! VehicleInformationViewController
        self.navigationController!.pushViewController(destinationVC, animated: true)
    }
    
}

extension VehicleViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int{
        return carArray.count/5
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: MenuOptionCell
        cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as! MenuOptionCell
        let vehicleOptionImage = VehicleOption(rawValue:0)
        cell.iconImageView.image = vehicleOptionImage?.image
        var carName = ""
        index = indexPath.row * 5
        carName = carArray[index] + " " + carArray[index+1]
        cell.descriptionLabel.text = carName
        cell.subDescriptionLabel.text = carArray[index+4]
        return cell
}
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            let db = Firestore.firestore()
            let currentUser = Auth.auth().currentUser
            let user = db.collection("Users").document((currentUser?.uid)!)
            user.getDocument { (value, Error) in
                var getCarArray = (value!["Cars"] as? [String])!
                let deleteIndex = indexPath.row * 5
                for _ in 0..<5 {
                    getCarArray.remove(at: deleteIndex)
                }
                self.carArray = getCarArray
                self.vehicleTableView.reloadData()
                user.updateData(["Cars":getCarArray])
            }

        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
            let destinationVC = self.storyboard?.instantiateViewController(withIdentifier: "VehicleInformationViewController") as! VehicleInformationViewController
            destinationVC.passCarIndex = indexPath.row * 5
            destinationVC.cameFromVehicleMenu = true
            self.navigationController!.pushViewController(destinationVC, animated: true)
    }
    
    
}
