//
//  ThirdViewController.swift
//  OpenSpot
//
//  Created by Stephen Fung and Jay Lliguichushca on 2/15/19.
//  Copyright © 2019 Stephen Fung. All rights reserved.
//

import UIKit
import FirebaseUI
import Firebase

class ThirdViewController: UIViewController, FUIAuthDelegate {
    @IBOutlet weak var menuTableView: UITableView!
    static var isLoggedOut: Bool = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        menuTableView.tableFooterView = UIView()
        // Do any additional setup after loading the view, typically from a nib.
        menuTableView.register(MenuOptionCell.self, forCellReuseIdentifier: "cell")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        if let index = self.menuTableView.indexPathForSelectedRow{
            self.menuTableView.deselectRow(at: index, animated: false)
        }
    }
    
    override func viewDidAppear(_ animated: Bool) {
        if ThirdViewController.isLoggedOut == true{
            self.tabBarController?.selectedIndex = 0
        }
        menuTableView.reloadData()
    }
    
}

extension ThirdViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int{
        return 6
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: MenuOptionCell
        cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as! MenuOptionCell
        let menuOption = MenuOption(rawValue: indexPath.row)
        cell.iconImageView.image = menuOption?.image
        
        if indexPath.row == 0 {
            let db = Firestore.firestore()
            let currentUser = Auth.auth().currentUser
            db.collection("Users").document((currentUser?.uid)!).getDocument { (value, Error) in
                cell.descriptionLabel.text = value!["fullName"] as? String
            }
            return cell
        }
        else{
            cell.descriptionLabel.text = menuOption?.description
            return cell
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        switch indexPath.row{
        case 0:
            let storyboard = UIStoryboard(name: "Main", bundle: nil)
            let controller = storyboard.instantiateViewController(withIdentifier: "NavigationController")
            self.present(controller, animated: false, completion: nil)
            print("0")
        case 1:
            print("1")
        case 2:
            print("2")
        case 3:
            let storyboard = UIStoryboard(name: "Main", bundle: nil)
            let controller = storyboard.instantiateViewController(withIdentifier: "VehicleNavigationController")
            self.present(controller, animated: false, completion: nil)
            print("3")
        case 4:
            let storyboard = UIStoryboard(name: "Main", bundle: nil)
            let controller = storyboard.instantiateViewController(withIdentifier: "SupportNavigationController")
            self.present(controller, animated: false, completion: nil)
            print("4")
        case 5:
            logOut()
        default:
            print("else")
        }
    }
    
    func logOut(){
        try! Auth.auth().signOut()
        if self.storyboard != nil {
            ThirdViewController.isLoggedOut = true
            let authUI = FUIAuth.defaultAuthUI()
            FUIAuth.defaultAuthUI()?.shouldHideCancelButton = true
            authUI?.delegate = self
            let providers: [FUIAuthProvider] = [FUIPhoneAuth(authUI:FUIAuth.defaultAuthUI()!),]
            authUI?.providers = providers
            let authViewController = OpenSpotFirebaseUI(authUI: authUI!)
            let navc = UINavigationController(rootViewController: authViewController)
            self.present(navc, animated: false, completion: nil)
        }
    }
    
}


