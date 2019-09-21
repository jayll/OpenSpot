//
//  MapFilterViewController.swift
//  OpenSpot
//
//  Created by Stephen Fung on 9/21/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import UIKit

class MapFilterViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {
    @IBOutlet weak var menuTableView: UITableView!
    var pricePickerView = UIPickerView()
    var minPrice = PriceSingleton.shared.minPrice
    var maxPrice = PriceSingleton.shared.maxPrice
    var minPriceArr = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29]
    let maxPricePossible = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]
    var maxPriceArr = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.title = "Map Filters"
        navigationItem.leftBarButtonItem = UIBarButtonItem(title: "Close", style: .plain, target: self, action:  #selector(dismissViewController))
        
        menuTableView.tableFooterView = UIView()
        // Do any additional setup after loading the view, typically from a nib.
        menuTableView.register(MapFilterOptionCell.self, forCellReuseIdentifier: "cell")
        
        pricePickerView.delegate = self
    }
    
    override func viewWillAppear(_ animated: Bool) {
        if let index = self.menuTableView.indexPathForSelectedRow {
            self.menuTableView.deselectRow(at: index, animated: false)
        }
        pricePickerView.selectRow(PriceSingleton.shared.minPrice, inComponent: 0, animated: false)
        pricePickerView.selectRow(maxPriceArr.count - PriceSingleton.shared.minPrice - 1, inComponent: 1, animated: false)
        menuTableView.reloadData()
    }
    
    @objc func dismissViewController() {
        dismiss(animated: true, completion: nil)
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == 0 {
            return minPriceArr.count
        } else {
            return maxPriceArr.count
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if component == 0 {
            minPrice = minPriceArr[row]
        } else {
            maxPrice = maxPriceArr[row]
        }
        DispatchQueue.main.async {
            self.menuTableView.beginUpdates()
            let selectedIndexPath = IndexPath(item:0 , section: 0)
            self.menuTableView.reloadRows(at: [selectedIndexPath], with: .none)
            self.menuTableView.endUpdates()
            
            let cell = self.menuTableView.cellForRow(at: selectedIndexPath) as! MapFilterOptionCell
            cell.rightTextField.becomeFirstResponder()
        }
        maxPriceArr = maxPricePossible
        maxPriceArr = maxPriceArr.filter({$0 > minPrice})
        if maxPriceArr.count > pickerView.selectedRow(inComponent: 1) {
            maxPrice = maxPriceArr[pickerView.selectedRow(inComponent: 1)]
        } else {
            maxPrice = 31
        }
        
        PriceSingleton.shared.minPrice = minPrice
        PriceSingleton.shared.maxPrice = maxPrice
        pricePickerView.reloadAllComponents()
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if component == 0 {
            return String(minPriceArr[row])
        } else {
            return String(maxPriceArr[row])
        }
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 2
    }
    
}

extension MapFilterViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int{
        return 3
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: MapFilterOptionCell
        cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as! MapFilterOptionCell
        let menuOption = MapFilterMenuOptions(rawValue: indexPath.row)
        cell.iconImageView.image = menuOption?.image
        cell.descriptionLabel.text = menuOption?.description
        if indexPath.row == 0 {
            cell.rightTextField.text = "$\(minPrice) - $\(maxPrice)"
            cell.rightTextField.inputView = pricePickerView
        } else {
            cell.rightTextField.isEnabled = false
        }
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        switch indexPath.row {
        case 0:
            
            print("0")
        case 1:
            
            print("1")
            
        case 2:
            
            print("2")
        default:
            print("else")
        }
    }
    
}
