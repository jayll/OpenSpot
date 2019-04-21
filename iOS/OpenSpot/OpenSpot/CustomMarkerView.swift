//
//  CustomMarkerView.swift
//  OpenSpot
//
//  Created by Jay Lliguichushca on 4/14/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import Foundation
import UIKit

class CustomMarkerView: UIView {
    var img: UIImage!
    var borderColor: UIColor!
    var priceText: String!
    
    init(frame: CGRect, image: UIImage, price: String) {
        super.init(frame: frame)
        self.img = image
        self.priceText = price
        setupViews()
    }
    
    func setupViews() {
        let imgView = UIImageView(image: img)
        imgView.frame = CGRect(x: 0, y: 0, width: 50, height: 30)
        imgView.clipsToBounds = true
        let lbl = UILabel(frame: CGRect(x: 2.5, y: -2, width: 45, height: 25))
        lbl.text = priceText
        lbl.font = UIFont.boldSystemFont(ofSize: 11)
        lbl.textColor = .white
        lbl.textAlignment = .center
        self.addSubview(imgView)
        self.bringSubviewToFront(lbl)
        self.addSubview(lbl)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
