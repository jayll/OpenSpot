//
//  OpenSpotFirebaseUI.swift
//  OpenSpot
//
//  Created by Jay Lliguichushca on 3/20/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import Firebase
import UIKit
import FirebaseUI

class OpenSpotFirebaseUI: FUIAuthPickerViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let width1 = UIScreen.main.bounds.size.width
        let height1 = UIScreen.main.bounds.size.height
        
        
        let imageViewBackground = UIImageView(frame: CGRect(x:width1/2-107, y: height1/2-170, width: 214, height:197))
        imageViewBackground.image = UIImage(named:"OpenSpot2")
//        imageViewBackground.contentMode = UIView.ContentMode.scaleAspectFill
        view.insertSubview(imageViewBackground, at: 1)
        
    }
    
    
}
