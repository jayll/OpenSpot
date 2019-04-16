//
//  OpenSpotTests.swift
//  OpenSpotTests
//
//  Created by Stephen Fung and Jay Lliguichushca on 2/15/19.
//  Copyright © 2019 Stephen Fung. All rights reserved.
//

import XCTest
import Firebase
@testable import OpenSpot

class OpenSpotTestsSprint1: XCTestCase {
    let firstViewController = FirstViewController()
    let thirdViewController = ThirdViewController()
    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }
    
    func testAllowUsersToLogOut_18(){
        if Auth.auth().currentUser != nil {
            thirdViewController.logOut()
        }
        XCTAssertNil(Auth.auth().currentUser)
    }

    func testPerformanceExample() {
        // This is an example of a performance test case.
        self.measure {
            // Put the code you want to measure the time of here.
        }
    }

}
