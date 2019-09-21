//
//  MenuOptionCell.swift
//  OpenSpot
//
//  Created by Stephen Fung and Jay Lliguichushca on 2/15/19.
//  Copyright © 2019 Stephen Fung. All rights reserved.
//

import UIKit

class MapFilterOptionCell: UITableViewCell {
    // MARK: - Properties
    
    let iconImageView: UIImageView = {
        let iv = UIImageView()
        iv.contentMode = .scaleAspectFit
        iv.clipsToBounds = true
        return iv
    }()
    
    let descriptionLabel: UILabel = {
        let label = UILabel()
        //        label.textColor = .white
        label.font = UIFont.systemFont(ofSize: 16)
        //        label.text = "Sample text"
        return label
    }()
    
    let rightTextField: UITextField = {
        var textField = UITextField()
        textField.textAlignment = .right
        textField.tintColor = .clear
        return textField
    }()
    
    // MARK: - Init
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: "cell")
        separatorInset = UIEdgeInsets.init(top: 0, left: 8, bottom: 0, right: 8)
        addSubview(iconImageView)
        iconImageView.translatesAutoresizingMaskIntoConstraints = false
        iconImageView.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        iconImageView.leftAnchor.constraint(equalTo: leftAnchor, constant: 12).isActive = true
        iconImageView.heightAnchor.constraint(equalToConstant: 24).isActive = true
        iconImageView.widthAnchor.constraint(equalToConstant: 24).isActive = true
        
        addSubview(descriptionLabel)
        descriptionLabel.translatesAutoresizingMaskIntoConstraints = false
        descriptionLabel.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        descriptionLabel.leftAnchor.constraint(equalTo: iconImageView.rightAnchor, constant: 12).isActive = true
        descriptionLabel.rightAnchor.constraint(equalTo: rightAnchor, constant: -140).isActive = true
        
        addSubview(rightTextField)
        rightTextField.translatesAutoresizingMaskIntoConstraints = false
        rightTextField.centerYAnchor.constraint(equalTo: centerYAnchor, constant: 0).isActive = true
        rightTextField.leftAnchor.constraint(equalTo: descriptionLabel.rightAnchor, constant: 0).isActive = true
        rightTextField.rightAnchor.constraint(equalTo: rightAnchor, constant: -12).isActive = true
        rightTextField.heightAnchor.constraint(equalToConstant: 24).isActive = true
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    // MARK: - Handlers
    
}
