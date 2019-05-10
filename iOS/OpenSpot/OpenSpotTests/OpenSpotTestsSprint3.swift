//
//  OpenSpotTestsSprint3.swift
//  OpenSpotTests
//
//  Created by Stephen Fung on 5/3/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import XCTest
import Firebase
@testable import OpenSpot

class OpenSpotTestsSprint3: XCTestCase {
    let firstViewController = FirstViewController()
    let thirdViewController = ThirdViewController()
    
    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }
    
    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }
    
    //    Stephen
    func testPullDataAndPopulateMap_64(){
        var availableDriveways:[String] = []
        let db = Firestore.firestore()
        
        // create the expectation
        let exp = expectation(description: "Loading available driveways")
        
        db.collection("Users").getDocuments { (value, error) in
            for account in value!.documents{
                let address = account.data()["Addresses"] as? [String]
                if address != nil && address!.count > 0{
                    var index = 0
                    while index != address!.count{
                        if address![index + 3] == "1"{
                            availableDriveways += ["Lat: \(address![index + 1]), Lon: \(address![index + 2]), $\(address![index + 4])/hr"]
                        }
                        index += 5
                    }
                }
            }
            exp.fulfill()
        }
        waitForExpectations(timeout: 10)
        print(availableDriveways.joined(separator: "\n"))
    }
    
    //    Stephen
    func testImplementMarkerFunctionality_81() {
        var availableDriveways:[String] = []
        let db = Firestore.firestore()
        
        // create the expectation
        let exp = expectation(description: "Loading available driveways")
        
        db.collection("Users").getDocuments { (value, error) in
            for account in value!.documents{
                let address = account.data()["Addresses"] as? [String]
                if address != nil && address!.count > 0{
                    var index = 0
                    while index != address!.count{
                        if address![index + 3] == "1"{
                            availableDriveways += ["Location Name: \(address![index]), $\(address![index + 4])/hr"]
                        }
                        index += 5
                    }
                }
            }
            exp.fulfill()
        }
        waitForExpectations(timeout: 10)
        print(availableDriveways.joined(separator: "\n"))
    }
    
}
