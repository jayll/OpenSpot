//
//  OpenSpotUITestsSprint2.swift
//  OpenSpotUITests
//
//  Created by Stephen Fung on 4/5/19.
//  Copyright © 2019 Jay Lliguichushca. All rights reserved.
//

import XCTest
import Firebase
@testable import OpenSpot

class OpenSpotUITestsSprint2: XCTestCase {
    
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
    
    //    Stephen
    func testAddGeneralData_38() {
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
            app/*@START_MENU_TOKEN@*/.keys["0"]/*[[".keyboards.keys[\"0\"]",".keys[\"0\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["2"]/*[[".keyboards.keys[\"2\"]",".keys[\"2\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["2"]/*[[".keyboards.keys[\"2\"]",".keys[\"2\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["0"]/*[[".keyboards.keys[\"0\"]",".keys[\"0\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["1"]/*[[".keyboards.keys[\"1\"]",".keys[\"1\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["9"]/*[[".keyboards.keys[\"9\"]",".keys[\"9\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["9"]/*[[".keyboards.keys[\"9\"]",".keys[\"9\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["8"]/*[[".keyboards.keys[\"8\"]",".keys[\"8\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            
            while !app.toolbars["Toolbar"].buttons["Done"].exists {}
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.buttons["NextArrow"].tap()
            while !app.navigationBars["Vehicle Information"].exists {}
            app.buttons["Skip"].tap()
        }
    }
    
    //    Jay
    func testInputVehicle_39() {
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
        
        
    }
    
    //    Jay and Stephen
    func testSendToFirebase_40() {
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
            app/*@START_MENU_TOKEN@*/.pickerWheels["Black"]/*[[".pickers.pickerWheels[\"Black\"]",".pickerWheels[\"Black\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "Silver")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.buttons["Check"].tap()
        }
        
        while !app.tabBars.buttons["Settings"].exists {}
        app.tabBars.buttons["Settings"].tap()
        app.tables/*@START_MENU_TOKEN@*/.staticTexts["Vehicle"]/*[[".cells.staticTexts[\"Vehicle\"]",".staticTexts[\"Vehicle\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        while !app.navigationBars["Vehicles"].exists{}
        sleep(1)
        app.navigationBars["Vehicles"].buttons["Add"].tap()
        while !app.navigationBars["Vehicle Information"].exists{}
        app.textFields["state"].tap()
        app/*@START_MENU_TOKEN@*/.pickerWheels["Alaska"]/*[[".pickers.pickerWheels[\"Alaska\"]",".pickerWheels[\"Alaska\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "New York")
        app.toolbars["Toolbar"].buttons["Done"].tap()
        app.textFields["license plate number(no dashes)"].tap()
        app.textFields["license plate number(no dashes)"].typeText("CSE442")
        app.toolbars["Toolbar"].buttons["Done"].tap()
        app.textFields["make"].tap()
        app.pickerWheels["Acura"].adjust(toPickerWheelValue: "Tesla")
        app.toolbars["Toolbar"].buttons["Done"].tap()
        app.textFields["model"].tap()
        app.pickerWheels["Model 3"].adjust(toPickerWheelValue: "Model S")
        app.toolbars["Toolbar"].buttons["Done"].tap()
        app.textFields["color"].tap()
        app/*@START_MENU_TOKEN@*/.pickerWheels["Black"]/*[[".pickers.pickerWheels[\"Black\"]",".pickerWheels[\"Black\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "Silver")
        app.toolbars["Toolbar"].buttons["Done"].tap()
        app.buttons["Check"].tap()
        sleep(1)
        
        XCTAssertTrue(app.tables.children(matching: .cell).element(boundBy: app.tables.children(matching: .cell).count-1).staticTexts["Tesla Model S"].exists)
    }
    
    //    Stephen
    func testEditPersonalData_42() {
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
            app/*@START_MENU_TOKEN@*/.keys["0"]/*[[".keyboards.keys[\"0\"]",".keys[\"0\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["2"]/*[[".keyboards.keys[\"2\"]",".keys[\"2\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["2"]/*[[".keyboards.keys[\"2\"]",".keys[\"2\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["0"]/*[[".keyboards.keys[\"0\"]",".keys[\"0\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["1"]/*[[".keyboards.keys[\"1\"]",".keys[\"1\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["9"]/*[[".keyboards.keys[\"9\"]",".keys[\"9\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["9"]/*[[".keyboards.keys[\"9\"]",".keys[\"9\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["8"]/*[[".keyboards.keys[\"8\"]",".keys[\"8\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            
            while !app.toolbars["Toolbar"].buttons["Done"].exists {}
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.buttons["NextArrow"].tap()
            while !app.navigationBars["Vehicle Information"].exists {}
            app.buttons["Skip"].tap()
        }
        while !app.tabBars.buttons["Settings"].exists {}
        app.tabBars.buttons["Settings"].tap()
        
        app.tables.firstMatch.cells.allElementsBoundByIndex.first?.tap()
        app.textFields["Email"].tap()
        
        app/*@START_MENU_TOKEN@*/.keys["delete"]/*[[".keyboards.keys[\"delete\"]",".keys[\"delete\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        app.keys["delete"].tap()
        app/*@START_MENU_TOKEN@*/.keys["delete"]/*[[".keyboards.keys[\"delete\"]",".keys[\"delete\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        app/*@START_MENU_TOKEN@*/.keys["c"]/*[[".keyboards.keys[\"c\"]",".keys[\"c\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        app/*@START_MENU_TOKEN@*/.keys["o"]/*[[".keyboards.keys[\"o\"]",".keys[\"o\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        app/*@START_MENU_TOKEN@*/.keys["m"]/*[[".keyboards.keys[\"m\"]",".keys[\"m\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        app.toolbars["Toolbar"].buttons["Done"].tap()
        app.buttons["NextArrow"].tap()
        while !app.tabBars.buttons["Settings"].exists {}
        assert(app.tables.firstMatch.cells.allElementsBoundByIndex.first!.exists)
    }
    
    //   Stephen
    func testContactUs_43() {
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
            app/*@START_MENU_TOKEN@*/.keys["0"]/*[[".keyboards.keys[\"0\"]",".keys[\"0\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["2"]/*[[".keyboards.keys[\"2\"]",".keys[\"2\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["2"]/*[[".keyboards.keys[\"2\"]",".keys[\"2\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["0"]/*[[".keyboards.keys[\"0\"]",".keys[\"0\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["1"]/*[[".keyboards.keys[\"1\"]",".keys[\"1\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["9"]/*[[".keyboards.keys[\"9\"]",".keys[\"9\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["9"]/*[[".keyboards.keys[\"9\"]",".keys[\"9\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            app/*@START_MENU_TOKEN@*/.keys["8"]/*[[".keyboards.keys[\"8\"]",".keys[\"8\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
            
            while !app.toolbars["Toolbar"].buttons["Done"].exists {}
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.buttons["NextArrow"].tap()
            while !app.navigationBars["Vehicle Information"].exists {}
            app.buttons["Skip"].tap()
        }
        while !app.tabBars.buttons["Settings"].exists {}
        app.tabBars.buttons["Settings"].tap()
        
        app.tables.cells.element(boundBy: 4).tap()
        
        app.textFields["Subject"].tap()
        app.textFields["Subject"].typeText("This email is being sent from UI tests")
        
        
        app.children(matching: .window).element(boundBy: 0).children(matching: .other).element.children(matching: .other).element.children(matching: .other).element.children(matching: .other).element.children(matching: .other).element.children(matching: .textView).element.tap()
        app.children(matching: .window).element(boundBy: 0).children(matching: .other).element.children(matching: .other).element.children(matching: .other).element.children(matching: .other).element.children(matching: .other).element.children(matching: .textView).element.typeText("This is the body of the email being sent from UI tests")
        
        app.toolbars["Toolbar"].buttons["Done"].tap()
        app.buttons["SendMail"].tap()
        while !app.navigationBars["This email is being sent from UI tests"].buttons["Send"].exists {}
        app.navigationBars["This email is being sent from UI tests"].buttons["Send"].tap()
        
        assert(app.navigationBars["Contact us"].exists)
        app.navigationBars["Contact us"].otherElements["Contact us"].tap()
        app.navigationBars["Contact us"].buttons["Close"].tap()
        
        assert(app.tables.cells.element(boundBy: 4).exists)
    }
    
    //    Jay
    func testAddVehicles_44() {
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
        
        while !app.tabBars.buttons["Settings"].exists {}
        app.tabBars.buttons["Settings"].tap()
        app.tables/*@START_MENU_TOKEN@*/.staticTexts["Vehicle"]/*[[".cells.staticTexts[\"Vehicle\"]",".staticTexts[\"Vehicle\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        while !app.navigationBars["Vehicles"].exists{}
        sleep(1)
        if(!app.tables.staticTexts["Ferrari 488 Spider"].exists){
            app.navigationBars["Vehicles"].buttons["Add"].tap()
            while !app.navigationBars["Vehicle Information"].exists{}
            app.textFields["state"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["Alaska"]/*[[".pickers.pickerWheels[\"Alaska\"]",".pickerWheels[\"Alaska\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "California")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["license plate number(no dashes)"].tap()
            app.textFields["license plate number(no dashes)"].typeText("DOG123")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["make"].tap()
            app.pickerWheels["Acura"].adjust(toPickerWheelValue: "Ferrari")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["model"].tap()
            app.pickerWheels["488 GTB"].adjust(toPickerWheelValue: "488 Spider")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["color"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["Black"]/*[[".pickers.pickerWheels[\"Black\"]",".pickerWheels[\"Black\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "Red")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.buttons["Check"].tap()
            sleep(1)
        }
    }
    
    // Jay
    func testEditVehicles_44() {
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
        while !app.tabBars.buttons["Settings"].exists {}
        app.tabBars.buttons["Settings"].tap()
        app.tables/*@START_MENU_TOKEN@*/.staticTexts["Vehicle"]/*[[".cells.staticTexts[\"Vehicle\"]",".staticTexts[\"Vehicle\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        while !app.navigationBars["Vehicles"].exists{}
        sleep(1)
        if(!app.tables.staticTexts["Ferrari 488 Spider"].exists){
            app.navigationBars["Vehicles"].buttons["Add"].tap()
            while !app.navigationBars["Vehicle Information"].exists{}
            app.textFields["state"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["Alaska"]/*[[".pickers.pickerWheels[\"Alaska\"]",".pickerWheels[\"Alaska\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "California")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["license plate number(no dashes)"].tap()
            app.textFields["license plate number(no dashes)"].typeText("DOG123")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["make"].tap()
            app.pickerWheels["Acura"].adjust(toPickerWheelValue: "Ferrari")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["model"].tap()
            app.pickerWheels["488 GTB"].adjust(toPickerWheelValue: "488 Spider")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["color"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["Black"]/*[[".pickers.pickerWheels[\"Black\"]",".pickerWheels[\"Black\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "Red")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.buttons["Check"].tap()
        }
        app.tables.staticTexts["Ferrari 488 Spider"].tap()
        app.textFields["license plate number(no dashes)"].tap()
        app.keys["delete"].press(forDuration: 1.1)
        app.textFields["license plate number(no dashes)"].typeText("CAT456")
        app.textFields["make"].tap()
        app.pickerWheels["Acura"].adjust(toPickerWheelValue: "Maserati")
        app.toolbars["Toolbar"].buttons["Done"].tap()
        app.textFields["model"].tap()
        app.pickerWheels["Ghibli"].adjust(toPickerWheelValue: "Levante")
        app.toolbars["Toolbar"].buttons["Done"].tap()
        app.textFields["color"].tap()
        app/*@START_MENU_TOKEN@*/.pickerWheels["Black"]/*[[".pickers.pickerWheels[\"Black\"]",".pickerWheels[\"Black\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "Blue")
        app.toolbars["Toolbar"].buttons["Done"].tap()
        app.buttons["Check"].tap()
        sleep(1)
    }
    
    // Jay
    func testDeleteVehicles_44() {
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
        while !app.tabBars.buttons["Settings"].exists {}
        app.tabBars.buttons["Settings"].tap()
        app.tables/*@START_MENU_TOKEN@*/.staticTexts["Vehicle"]/*[[".cells.staticTexts[\"Vehicle\"]",".staticTexts[\"Vehicle\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        while !app.navigationBars["Vehicles"].exists{}
        sleep(1)
        if(!app.tables.staticTexts["Maserati Levante"].exists){
            app.navigationBars["Vehicles"].buttons["Add"].tap()
            while !app.navigationBars["Vehicle Information"].exists{}
            app.textFields["state"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["Alaska"]/*[[".pickers.pickerWheels[\"Alaska\"]",".pickerWheels[\"Alaska\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "New York")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["license plate number(no dashes)"].tap()
            app.textFields["license plate number(no dashes)"].typeText("CAT456")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["make"].tap()
            app.pickerWheels["Acura"].adjust(toPickerWheelValue: "Maserati")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["model"].tap()
            app.pickerWheels["Ghibli"].adjust(toPickerWheelValue: "Levante")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.textFields["color"].tap()
            app/*@START_MENU_TOKEN@*/.pickerWheels["Black"]/*[[".pickers.pickerWheels[\"Black\"]",".pickerWheels[\"Black\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.adjust(toPickerWheelValue: "Blue")
            app.toolbars["Toolbar"].buttons["Done"].tap()
            app.buttons["Check"].tap()
        }
        sleep(1)
        app.tables.staticTexts["Maserati Levante"].swipeLeft()
        app.tables.staticTexts["Maserati Levante"].swipeLeft()
        sleep(1)
    }
    
}
