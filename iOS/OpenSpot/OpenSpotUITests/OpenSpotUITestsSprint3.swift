//
//  OpenSpotUITestsSprint3.swift
//  OpenSpotUITests
//
//  Created by Stephen Fung on 5/3/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import XCTest
import Firebase
@testable import OpenSpot

class OpenSpotUITestsSprint3: XCTestCase {
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
    
//    //    Jay
//    func testAddLocationSearchBar_59() {
//    }
    
    //    Stephen
    func testAddDriveway_61() {
        let app = XCUIApplication()
        if app.scrollViews.otherElements.buttons["Sign in with phone"].exists{
            app.scrollViews.otherElements.buttons["Sign in with phone"].tap()
            let key = app.keyboards.keys["2"]
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
        sleep(3)
        if app.textFields["Full Name"].exists{
            app.textFields["Full Name"].tap()
            app.textFields["Full Name"].typeText("Jay Stephen")
            
            app.textFields["Email"].tap()
            app.textFields["Email"].typeText("jaystephen@gmail.com")
            
            app.textFields["MM"].tap()
            app.textFields["MM"].typeText("09141998")
            
            while !app.toolbars["Toolbar"].buttons["Done"].exists{}
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.buttons["NextArrow"].tap()
            while !app.navigationBars["Vehicle Information"].exists{}
            
            app.textFields["state"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["Alaska"]/*[[".pickers.pickerWheels[\"Alaska\"]",".pickerWheels[\"Alaska\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "New York")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["license plate number(no dashes)"].tap()
            app.textFields["license plate number(no dashes)"].typeText("BAV234")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["make"].tap()
            app.pickerWheels["Acura"].adjust(toPickerWheelValue: "Audi")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["model"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["A3"]/*[[".pickers.pickerWheels[\"A3\"]",".pickerWheels[\"A3\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "R8")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["color"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["Black"]/*[[".pickers.pickerWheels[\"Black\"]",".pickerWheels[\"Black\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "White")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.buttons["Check"].tap()
        }
        
        app.tabBars.buttons["Settings"].tap()
        app.tables/*@START_MENU_TOKEN@*/.staticTexts["List your driveway"]/*[[".cells.staticTexts[\"List your driveway\"]",".staticTexts[\"List your driveway\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        app.navigationBars["Driveways"].buttons["Add"].tap()
        
        
        app.children(matching: .window).element(boundBy: 0).children(matching: .other).element.children(matching: .other).element.children(matching: .other).element.children(matching: .other).element.children(matching: .other).element.children(matching: .other).element.children(matching: .other).element(boundBy: 0).children(matching: .textField).element.tap()
        
        app/*@START_MENU_TOKEN@*/.keys["S"]/*[[".keyboards.keys[\"S\"]",".keys[\"S\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        
        let eKey = app/*@START_MENU_TOKEN@*/.keys["e"]/*[[".keyboards.keys[\"e\"]",".keys[\"e\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/
        eKey.tap()

        
        let nKey = app/*@START_MENU_TOKEN@*/.keys["n"]/*[[".keyboards.keys[\"n\"]",".keys[\"n\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/
        nKey.tap()
        app.tables/*@START_MENU_TOKEN@*/.staticTexts["Seneca Niagara Resort & Casino"]/*[[".cells.staticTexts[\"Seneca Niagara Resort & Casino\"]",".staticTexts[\"Seneca Niagara Resort & Casino\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        app.sliders["priceSlider"].adjust(toNormalizedSliderPosition: 0.2)
        app/*@START_MENU_TOKEN@*/.buttons["Active"]/*[[".segmentedControls.buttons[\"Active\"]",".buttons[\"Active\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        app.buttons["Button"].tap()
        sleep(2)
        }
    
    //    Stephen
    func testRemoveDriveway_62() {
        let app = XCUIApplication()
        if app.scrollViews.otherElements.buttons["Sign in with phone"].exists{
            app.scrollViews.otherElements.buttons["Sign in with phone"].tap()
            let key = app.keyboards.keys["2"]
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
        sleep(3)
        if app.textFields["Full Name"].exists{
            app.textFields["Full Name"].tap()
            app.textFields["Full Name"].typeText("Jay Stephen")
            
            app.textFields["Email"].tap()
            app.textFields["Email"].typeText("jaystephen@gmail.com")
            
            app.textFields["MM"].tap()
            app.textFields["MM"].typeText("09141998")
            
            while !app.toolbars["Toolbar"].buttons["Done"].exists{}
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.buttons["NextArrow"].tap()
            while !app.navigationBars["Vehicle Information"].exists{}
            
            app.textFields["state"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["Alaska"]/*[[".pickers.pickerWheels[\"Alaska\"]",".pickerWheels[\"Alaska\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "New York")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["license plate number(no dashes)"].tap()
            app.textFields["license plate number(no dashes)"].typeText("BAV234")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["make"].tap()
            app.pickerWheels["Acura"].adjust(toPickerWheelValue: "Audi")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["model"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["A3"]/*[[".pickers.pickerWheels[\"A3\"]",".pickerWheels[\"A3\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "R8")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["color"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["Black"]/*[[".pickers.pickerWheels[\"Black\"]",".pickerWheels[\"Black\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "White")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.buttons["Check"].tap()
        }
        
        app.tabBars.buttons["Settings"].tap()
        app.tables/*@START_MENU_TOKEN@*/.staticTexts["List your driveway"]/*[[".cells.staticTexts[\"List your driveway\"]",".staticTexts[\"List your driveway\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        app.tables.staticTexts["Seneca Niagara Resort & Casino"].swipeLeft()
        app.tables.staticTexts["Seneca Niagara Resort & Casino"].swipeLeft()
        
        
        sleep(2)
    }
    
    //    Stephen
    func testEditDriveway_63() {
        let app = XCUIApplication()
        if app.scrollViews.otherElements.buttons["Sign in with phone"].exists{
            app.scrollViews.otherElements.buttons["Sign in with phone"].tap()
            let key = app.keyboards.keys["2"]
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
        sleep(3)
        if app.textFields["Full Name"].exists{
            app.textFields["Full Name"].tap()
            app.textFields["Full Name"].typeText("Jay Stephen")
            
            app.textFields["Email"].tap()
            app.textFields["Email"].typeText("jaystephen@gmail.com")
            
            app.textFields["MM"].tap()
            app.textFields["MM"].typeText("09141998")
            
            while !app.toolbars["Toolbar"].buttons["Done"].exists{}
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.buttons["NextArrow"].tap()
            while !app.navigationBars["Vehicle Information"].exists{}
            
            app.textFields["state"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["Alaska"]/*[[".pickers.pickerWheels[\"Alaska\"]",".pickerWheels[\"Alaska\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "New York")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["license plate number(no dashes)"].tap()
            app.textFields["license plate number(no dashes)"].typeText("BAV234")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["make"].tap()
            app.pickerWheels["Acura"].adjust(toPickerWheelValue: "Audi")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["model"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["A3"]/*[[".pickers.pickerWheels[\"A3\"]",".pickerWheels[\"A3\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "R8")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["color"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["Black"]/*[[".pickers.pickerWheels[\"Black\"]",".pickerWheels[\"Black\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "White")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.buttons["Check"].tap()
        }
        
        app.tabBars.buttons["Settings"].tap()
        app.tables/*@START_MENU_TOKEN@*/.staticTexts["List your driveway"]/*[[".cells.staticTexts[\"List your driveway\"]",".staticTexts[\"List your driveway\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        
        app.tables/*@START_MENU_TOKEN@*/.staticTexts["Seneca Niagara Resort & Casino"]/*[[".cells.staticTexts[\"Seneca Niagara Resort & Casino\"]",".staticTexts[\"Seneca Niagara Resort & Casino\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        app.sliders["priceSlider"].adjust(toNormalizedSliderPosition: 0.8)
        app.buttons["Inactive"].tap()
        app.buttons["Button"].tap()
        sleep(2)
    }
    
//    //    Jay
//    func testViewReservationAddress_83() {
//    }
//
//    //    Jay
//    func testViewReservationPirce_84() {
//    }
//
//    //    Jay
//    func testContactDrivewayOnwer_85() {
//    }
}
