//
//  SupportViewController.swift
//  OpenSpot
//
//  Created by Stephen Fung on 3/29/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import UIKit
import MessageUI
import Firebase

class SupportViewController: UIViewController, UITextViewDelegate{
    @IBOutlet weak var subjectTextField: UITextField!
    @IBOutlet weak var bodyTextView: UITextView!
    let bodyTextViewMaxHeight: CGFloat = 375
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.bodyTextView.text = "\n\n\n\n\n ------------------------------------------ \nPlease enter your questions/comments above this line" + "\n\nUID: " + Auth.auth().currentUser!.uid + "\nMetadata: " + Auth.auth().currentUser!.metadata.description + "\n"
        
        subjectTextField.underlined()
        bodyTextView.layer.borderColor = UIColor.gray.withAlphaComponent(0.5).cgColor
        bodyTextView.layer.borderWidth = 0.5
        bodyTextView.clipsToBounds = true
        self.bodyTextView.delegate = self
        
        self.navigationItem.title = "Contact us"
        let btnBack = UIBarButtonItem(title: "Close", style:.done, target: self, action: #selector(self.dismissVC))
        btnBack.setTitleTextAttributes([NSAttributedString.Key.foregroundColor: #colorLiteral(red: 0.2319215834, green: 0.5326585174, blue: 0.9921949506, alpha: 1)], for: .normal)
        self.navigationItem.setLeftBarButton(btnBack, animated: true)
        
        
    }
    
    @objc func dismissVC() {
        self.dismiss(animated: true, completion: nil)
    }
    func textViewDidChange(_ textView: UITextView){
        if textView.contentSize.height >= self.bodyTextViewMaxHeight{
            textView.isScrollEnabled = true
        }
        else{
            textView.frame.size.height = textView.contentSize.height
            textView.isScrollEnabled = false
        }
    }
    
    @IBAction func sendMail(_ sender: Any) {
        if subjectTextField.text!.count > 0 {
            sendEmail()
        }
        else{
            let alertController = UIAlertController(title: "", message: "Please insert a subject line", preferredStyle: UIAlertController.Style.alert)
            alertController.addAction(UIAlertAction(title: "Ok", style: UIAlertAction.Style.default, handler: nil))
            self.present(alertController, animated: true, completion: nil)
        }
    }
    
}

extension SupportViewController: MFMailComposeViewControllerDelegate {
    func sendEmail() {
        if MFMailComposeViewController.canSendMail() {
            let mail = MFMailComposeViewController()
            mail.mailComposeDelegate = self
            mail.setToRecipients(["csecse442+support@gmail.com"])
            mail.setSubject(subjectTextField.text!)
            mail.setMessageBody(bodyTextView.text!, isHTML: false)
            present(mail, animated: true)
        } else {
            // show failure alert
        }
    }
    
    func mailComposeController(_ controller: MFMailComposeViewController, didFinishWith result: MFMailComposeResult, error: Error?) {
        controller.dismiss(animated: true)
    }
}
