//
//  OpenSpotUITests.swift
//  OpenSpotUITests
//
//  Created by Stephen Fung and Jay Lliguichushca on 2/15/19.
//  Copyright © 2019 Stephen Fung. All rights reserved.
//

import XCTest
import Firebase
@testable import OpenSpot

class OpenSpotUITests: XCTestCase {

    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
        
        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false
        
        // UI tests must launch the application that they test. Doing this in setup will make sure it happens for each test method.
        XCUIApplication().launch()
    }
    
    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }
    
    func testBootUpApplicationDrivewayOwners_2() {
    }
    
    func testBootUpApplicationCommuters_3() {
    }
    
    func testAllowUsersToLogIn_31(){
        let app = XCUIApplication()
        if app.scrollViews.otherElements.buttons["Sign in with phone"].exists{
            app.scrollViews.otherElements.buttons["Sign in with phone"].tap()
            let key = app.keyboards.keys["1"]
            while !key.exists {
            }
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            app.navigationBars["Enter phone number"].buttons["Verify"].tap()
            while !key.exists {
            }
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            app.navigationBars["Verify phone number"].buttons["Next"].tap()
        }
    }
    
    func testAllowUsersToLogOut_18(){
        let app = XCUIApplication()
        if app.scrollViews.otherElements.buttons["Sign in with phone"].exists{
            app.scrollViews.otherElements.buttons["Sign in with phone"].tap()
            let key = app.keyboards.keys["1"]
            while !key.exists {
            }
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            app.navigationBars["Enter phone number"].buttons["Verify"].tap()
            while !key.exists {
            }
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            app.navigationBars["Verify phone number"].buttons["Next"].tap()
        }
        while !app.tabBars.buttons["Settings"].exists {
        }
        app.tabBars.buttons["Settings"].tap()
        app.tables.staticTexts["Log out"].tap()
    }
    
    func testImplementMapOnMainActivity_22(){
        let app = XCUIApplication()
        if app.scrollViews.otherElements.buttons["Sign in with phone"].exists{
            app.scrollViews.otherElements.buttons["Sign in with phone"].tap()
            let key = app.keyboards.keys["1"]
            while !key.exists {
            }
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            app.navigationBars["Enter phone number"].buttons["Verify"].tap()
            while !key.exists {
            }
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            app.navigationBars["Verify phone number"].buttons["Next"].tap()
        }
        XCTAssertEqual(XCUIApplication().otherElements["My Location"].label, "My Location")
    }
    
    func testBottomNavigationBar_23(){
        let app = XCUIApplication()
        if app.scrollViews.otherElements.buttons["Sign in with phone"].exists{
            app.scrollViews.otherElements.buttons["Sign in with phone"].tap()
            let key = app.keyboards.keys["1"]
            while !key.exists {
            }
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            app.navigationBars["Enter phone number"].buttons["Verify"].tap()
            while !key.exists {
            }
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            key.tap()
            app.navigationBars["Verify phone number"].buttons["Next"].tap()
        }
        let tabBarsQuery = XCUIApplication().tabBars
        let parkingButton = tabBarsQuery.buttons["Parking"]
        parkingButton.tap()
        let reservationsButton = tabBarsQuery.buttons["Reservations"]
        reservationsButton.tap()
        let settingsButton = tabBarsQuery.buttons["Settings"]
        settingsButton.tap()
        parkingButton.tap()
        reservationsButton.tap()
        settingsButton.tap()
    }


}
