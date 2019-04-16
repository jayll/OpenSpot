//
//  UITextFieldExtension.swift
//  OpenSpot
//
//  Created by Stephen Fung on 3/9/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//
import UIKit

class UITextFieldExtension: UITextField {
    
    //    let padding = UIEdgeInsets(top: 4, left: 12, bottom: 4, right: 12)
    //
    //    override open func textRect(forBounds bounds: CGRect) -> CGRect {
    //        return bounds.inset(by: padding)
    //    }
    //
    //    override open func placeholderRect(forBounds bounds: CGRect) -> CGRect {
    //        return bounds.inset(by: padding)
    //    }
    //
    //    override open func editingRect(forBounds bounds: CGRect) -> CGRect {
    //        return bounds.inset(by: padding)
    //    }
    //
    //    @IBInspectable var borderColor: UIColor? {
    //        didSet {
    //            layer.borderColor = borderColor?.cgColor
    //        }
    //    }
    //    @IBInspectable var borderWidth: CGFloat = 0 {
    //        didSet {
    //            layer.borderWidth = borderWidth
    //        }
    //    }
}

extension UITextField{
    
    @IBInspectable var doneAccessory: Bool{
        get{
            return self.doneAccessory
        }
        set (hasDone) {
            if hasDone{
                addDoneButtonOnKeyboard()
            }
        }
    }
    
    func addDoneButtonOnKeyboard(){
        let doneToolbar: UIToolbar = UIToolbar(frame: CGRect.init(x: 0, y: 0, width: UIScreen.main.bounds.width, height: 50))
        doneToolbar.barStyle = .default
        
        let flexSpace = UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil)
        let done: UIBarButtonItem = UIBarButtonItem(title: "Done", style: .done, target: self, action: #selector(self.doneButtonAction))
        
        let items = [flexSpace, done]
        doneToolbar.items = items
        doneToolbar.sizeToFit()
        self.inputAccessoryView = doneToolbar
    }
    
    @objc func doneButtonAction() {
        self.resignFirstResponder()
    }
    
    func underlined(){
        let border = CALayer()
        let width = CGFloat(1.5)
        let purple = #colorLiteral(red: 0.6156862745, green: 0.6039215686, blue: 0.937254902, alpha: 1)
        border.borderColor = purple.cgColor
        border.frame = CGRect(x: 0, y: self.frame.size.height + 0.6 - width , width:  self.frame.size.width, height: self.frame.size.height)
        border.borderWidth = width
        self.layer.addSublayer(border)
        self.layer.masksToBounds = true
    }
    
    func underlinedBlack(){
        let border = CALayer()
        let width = CGFloat(1.5)
        let purple = #colorLiteral(red: 0, green: 0, blue: 0, alpha: 1)
        border.borderColor = purple.cgColor
        border.frame = CGRect(x: 0, y: self.frame.size.height + 0.6 - width , width:  self.frame.size.width, height: self.frame.size.height)
        border.borderWidth = width
        self.layer.addSublayer(border)
        self.layer.masksToBounds = true
        self.isEnabled = false
    }
    
}

extension UITextView{
    @IBInspectable var doneAccessory: Bool{
        get{
            return self.doneAccessory
        }
        set (hasDone) {
            if hasDone{
                addDoneButtonOnKeyboard()
            }
        }
    }
    
    func addDoneButtonOnKeyboard(){
        let doneToolbar: UIToolbar = UIToolbar(frame: CGRect.init(x: 0, y: 0, width: UIScreen.main.bounds.width, height: 50))
        doneToolbar.barStyle = .default
        
        let flexSpace = UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil)
        let done: UIBarButtonItem = UIBarButtonItem(title: "Done", style: .done, target: self, action: #selector(self.doneButtonAction))
        
        let items = [flexSpace, done]
        doneToolbar.items = items
        doneToolbar.sizeToFit()
        self.inputAccessoryView = doneToolbar
    }
    
    @objc func doneButtonAction() {
        self.resignFirstResponder()
    }
}
