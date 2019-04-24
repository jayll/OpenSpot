//
//  UserInformation.swift
//  OpenSpot
//
//  Created by Stephen Fung on 3/8/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import UIKit
import Firebase

class UserInformationViewController: UIViewController, UITextFieldDelegate {
    
    @IBOutlet weak var fullNameTextField: UITextField!
    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var monthTextField: UITextField!
    @IBOutlet weak var dayTextField: UITextField!
    @IBOutlet weak var yearTextField: UITextField!
    @IBOutlet weak var phoneNumberTextField: UITextField!
    
    var newUser = true
    let db = Firestore.firestore()
    let currentUser = Auth.auth().currentUser
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        fullNameTextField.underlined()
        emailTextField.underlined()
        dayTextField.underlined()
        monthTextField.underlined()
        yearTextField.underlined()
        phoneNumberTextField.underlinedBlack()
        let currentUser = Auth.auth().currentUser
        var phoneNumberWithSpaces = currentUser!.phoneNumber
        phoneNumberWithSpaces!.insert(string: " (", ind: 2)
        phoneNumberWithSpaces!.insert(string: ") ", ind: 7)
        phoneNumberWithSpaces!.insert(string: "-", ind: 12)
        self.phoneNumberTextField.text = phoneNumberWithSpaces
        
        self.title = "User information"
        checkAccountExists()
    }
    
    func checkAccountExists(){
        db.collection("Users").document((currentUser?.uid)!).getDocument { (value, Error) in
            if value?["fullName"] != nil{
                self.newUser = false
                self.fullNameTextField.underlinedBlack()
                self.monthTextField.underlinedBlack()
                self.dayTextField.underlinedBlack()
                self.yearTextField.underlinedBlack()
                
                self.fullNameTextField.text = value!["fullName"] as? String
                self.emailTextField.text = value!["email"] as? String
                let dob = value!["dateOfBirth"] as? String
                self.monthTextField.text = dob![0..<2]
                self.dayTextField.text = dob![3..<5]
                self.yearTextField.text = dob![6..<10]
                
                let btnBack = UIBarButtonItem(title: "Close", style:.done, target: self, action: #selector(self.dismissVC))
                btnBack.setTitleTextAttributes([NSAttributedString.Key.foregroundColor: #colorLiteral(red: 0.2319215834, green: 0.5326585174, blue: 0.9921949506, alpha: 1)], for: .normal)
                self.navigationItem.setLeftBarButton(btnBack, animated: true)
            }
        }
    }
    
    @objc func dismissVC() {
        self.dismiss(animated: true, completion: nil)
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool{
        // Try to find next responder
        if let nextField = self.view.viewWithTag(textField.tag + 1) as? UITextField {
            nextField.becomeFirstResponder()
        } else {
            // Not found, so remove keyboard.
            textField.resignFirstResponder()
        }
        // Do not add a line break
        return false
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        let char = string.cString(using: String.Encoding.utf8)
        let isBackSpace = strcmp(char, "\\b")
        if isBackSpace == -92 {
            return true
        }
        if textField == monthTextField || textField == dayTextField{
            if textField.text!.count == 1{
                if let nextField = self.view.viewWithTag(textField.tag + 1) as? UITextField {
                    textField.text = textField.text! + string
                    nextField.becomeFirstResponder()
                }
            }
            return textField.text!.count < 2
        }
        else if textField == yearTextField{
            return textField.text!.count < 4
        }
        return true
    }
}

extension UserInformationViewController: UIAlertViewDelegate{
    @IBAction func nextClicked(_ sender: Any) {
        if monthTextField.text?.count == 1 {
            monthTextField.text = "0" + monthTextField.text!
        }
        if dayTextField.text?.count == 1 {
            dayTextField.text = "0" +  dayTextField.text!
        }
        if !(fullNameTextField.text?.contains(" "))!{
            showErrorMessage(message: "Please Enter Your First And Last Name")
        }
        else if !(emailTextField.text?.contains("@"))! || !(emailTextField.text?.contains("."))!{
            showErrorMessage(message: "Please Enter A Valid Email")
        }
        else if Int(monthTextField.text!) ?? 13 > 13{
            showErrorMessage(message: "Please Enter Your Birth Month")
        }
        else if Int(dayTextField.text!) ?? 31 >= 31{
            showErrorMessage(message: "Please Enter Your Birth Day")
        }
        else if !((yearTextField.text?.count ?? 0) > 3) || (Int(yearTextField.text!) ?? 1900 < 1900 || Int(yearTextField.text!) ?? 1991 > 2004){
            showErrorMessage(message: "Please Enter Your Birth Year")
        }
        
        if newUser == true{
            let destinationVC = self.storyboard?.instantiateViewController(withIdentifier: "VehicleInformationViewController") as! VehicleInformationViewController
            destinationVC.fullName = fullNameTextField.text!
            destinationVC.email = emailTextField.text!
            destinationVC.dateOfBirth =  monthTextField.text! + "/" + dayTextField.text! + "/" + yearTextField.text!
            self.navigationController!.pushViewController(destinationVC, animated: true)
        }
        else{
            db.collection("Users").document((currentUser?.uid)!).updateData([
                "phoneNumber": currentUser?.phoneNumber! as Any,
                "fullName": fullNameTextField.text!,
                "email": emailTextField.text!,
                "dateOfBirth": monthTextField.text! + "/" + dayTextField.text! + "/" + yearTextField.text!
                ])
            
            self.dismiss(animated: true, completion: nil)
        }
    }
    
    func showErrorMessage(message : String) {
        let alertController = UIAlertController(title: "", message: message, preferredStyle: UIAlertController.Style.alert)
        alertController.addAction(UIAlertAction(title: "Ok", style: UIAlertAction.Style.default, handler: nil))
        self.present(alertController, animated: true, completion: nil)
    }
}

extension String {
    subscript(_ range: CountableRange<Int>) -> String {
        let idx1 = index(startIndex, offsetBy: max(0, range.lowerBound))
        let idx2 = index(startIndex, offsetBy: min(self.count, range.upperBound))
        return String(self[idx1..<idx2])
    }
        mutating func insert(string:String,ind:Int) {
            self.insert(contentsOf: string, at:self.index(self.startIndex, offsetBy: ind) )
        }
    
}
