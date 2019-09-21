//
//  DrivewayListViewController.swift
//  OpenSpot
//
//  Created by Stephen Fung on 4/12/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import UIKit
import Firebase

class DrivewayListViewController: UIViewController{
    @IBOutlet weak var drivewayTableView: UITableView!
    var drivewayArray = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.title = "Driveways"
        navigationItem.leftBarButtonItem = UIBarButtonItem(title: "Close", style: .plain, target: self, action:  #selector(dismissViewController))
        navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .add, target: self, action: #selector(newDrivewayViewController))
        
        drivewayTableView.tableFooterView = UIView()
        drivewayTableView.register(MenuOptionCell.self, forCellReuseIdentifier: "cell")
    }
    
    override func viewDidAppear(_ animated: Bool) {
        getDriveways()
    }
    
    @objc func newDrivewayViewController(){
        let destinationVC = self.storyboard?.instantiateViewController(withIdentifier: "DrivewayViewController") as! DrivewayViewController
        self.show(destinationVC, sender: nil)
    }
    
    @objc func dismissViewController(){
        dismiss(animated: true, completion: nil)
    }
    
    func getDriveways(){
        let db = Firestore.firestore()
        let currentUser = Auth.auth().currentUser
        db.collection("Users").document((currentUser?.uid)!).getDocument {(value, Error) in
            self.drivewayArray = value!["Addresses"] as? [String] ?? []
            self.drivewayTableView.reloadData()
            print(self.drivewayArray)
        }
    }
}

extension DrivewayListViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int{
        return drivewayArray.count/5
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as! MenuOptionCell
        if drivewayArray[indexPath.row * 5 + 3] == "1"{
            cell.iconImageView.image = #imageLiteral(resourceName: "DrivewayOpen")
            cell.subDescriptionLabel.text = "Active: " + "$" + drivewayArray[indexPath.row * 5 + 4] + "/hr"
        }
        else{
            cell.iconImageView.image = #imageLiteral(resourceName: "DrivewayClosed")
            cell.subDescriptionLabel.text = "Inactive: " + "$" + drivewayArray[indexPath.row * 5 + 4] + "/hr"
        }
        cell.descriptionLabel.text = drivewayArray[indexPath.row * 5]
        return cell
    }

    func tableView(_ tableView: UITableView, editActionsForRowAt indexPath: IndexPath) -> [UITableViewRowAction]? {
        var switchStatus: UITableViewRowAction
        if drivewayArray[indexPath.row * 5 + 3] == "1"{
            switchStatus = UITableViewRowAction(style: .normal, title: "Deactivate") { action, index in
                self.switchStatusFunc(indexPath: indexPath)
            }
        }else{
            switchStatus = UITableViewRowAction(style: .normal, title: "Activate") { action, index in
                self.switchStatusFunc(indexPath: indexPath)
            }
        }
        switchStatus.backgroundColor = .orange
        let delete = UITableViewRowAction(style: .normal, title: "Delete") { action, index in
            self.deleteFunc(indexPath: indexPath)
        }
        delete.backgroundColor = .red
        
        return [delete, switchStatus]
    }
    
    func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }
    
    func switchStatusFunc(indexPath: IndexPath) {
        let db = Firestore.firestore()
        let currentUser = Auth.auth().currentUser
        let user = db.collection("Users").document((currentUser?.uid)!)
        user.getDocument { (value, Error) in
            if self.drivewayArray[indexPath.row * 5 + 3] == "1"{
                self.drivewayArray[indexPath.row * 5 + 3] = "0"
            }else{
                self.drivewayArray[indexPath.row * 5 + 3] = "1"
            }
            self.drivewayTableView.reloadData()
            user.updateData(["Addresses" : self.drivewayArray])
        }
    }
    
    func deleteFunc(indexPath: IndexPath) {
        let db = Firestore.firestore()
        let currentUser = Auth.auth().currentUser
        let user = db.collection("Users").document((currentUser?.uid)!)
        user.getDocument { (value, Error) in
            let deleteIndex = indexPath.row * 5
            for _ in 0..<5 {
                self.drivewayArray.remove(at: deleteIndex)
            }
            self.drivewayTableView.reloadData()
            user.updateData(["Addresses" : self.drivewayArray])
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let destinationVC = self.storyboard?.instantiateViewController(withIdentifier: "DrivewayViewController") as! DrivewayViewController
        destinationVC.addressIndex = indexPath.row * 5
        self.navigationController!.pushViewController(destinationVC, animated: true)
    }
    
}
