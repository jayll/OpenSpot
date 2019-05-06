package com.example.openspot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.view.ViewGroup
import android.widget.*
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_vehicle_info.*


class VehicleInfoActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener{
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val TAG = "VehicleInfoActivity"
        var acuraModel = arrayListOf("ILX", "MDX", "MDX Sport Hybrid", "NSX", "RDX", "RLX", "RLX Sport Hybrid", "TLX")
        val alfaRomeoModel = arrayListOf("4C", "4C Spider", "Giulia", "Stelvio")
        var astonMartinModels = arrayListOf("DB11", "Vanquish S")
        var audiModels = arrayListOf("A3", "A3 Sportback e-tron", "A4", "A4 allroad", "A5", "A6", "A7", "A8", "e-tron", "Q3", "Q5", "Q7", "Q8", "R8", "RS 3", "RS 5", "RS 7", "S3", "S4", "S5", "S6", "S7", "S8", "SQ5", "TT")
        var bentleyModels = arrayListOf("Bentayga", "Continental", "Flying Spur", "Mulsanne")
        var bmwModels = arrayListOf("2 Series", "3 Series", "4 Series", "5 Series", "6 Series", "7 Series", "i3", "i8", "M2", "M3", "M4", "M5", "M6", "X1", "X2", "X3", "X4", "X5", "X5 M", "X6", "X6 M")
        var buickModels = arrayListOf("Cascada", "Enclave", "Encore", "Envision", "LaCrosse", "Regal Sportback", "Regal TourX")
        var cadillacModels = arrayListOf("ATS", "ATS-V", "CT6", "CTS", "CTS-V", "Escalade", "Escalade ESV", "XT4", "XT5", "XTS")
        var chevroletModels = arrayListOf("Blazer", "Bolt EV", "Camaro", "City Express", "Colorado Crew Cab", "Colorado Extended Cab", "Corvette", "Cruze", "Equinox", "Express 2500 Cargo", "Express 2500 Passenger", "Express 3500 Cargo", "Express 3500 Passenger", "Impala", "Malibu", "Silverado 1500 Crew Cab", "Silverado 1500 Double Cab", "Silverado 1500 LD Double Cab", "Silverado 1500 Regular Cab", "Silverado 2500 HD Crew Cab", "Silverado 2500 HD Double Cab", "Silverado 2500 HD Regular Cab", "Silverado 3500 HD Crew Cab", "Silverado 3500 HD Double Cab", "Silverado 3500 HD Regular Cab", "Sonic", "Spark", "Suburban", "Tahoe", "Traverse", "Trax", "Volt")
        var chryslerModels = arrayListOf("300", "Pacifica", "Pacifica Hybrid")
        var dodgeModels = arrayListOf("Challenger", "Charger", "Durango", "Grand Caravan Passenger", "Journey")
        var ferrariModels = arrayListOf("488 GTB", "488 Spider", "812 Superfast", "California", "F12berlinetta", "GTC4Lusso", "Portofino")
        var fiatModels = arrayListOf("124 Spider", "500", "500 Abarth", "500c", "500c Abarth", "500e", "500L", "500X")
        var fordModels = arrayListOf("C-MAX Hybrid", "EcoSport", "Edge", "Escape", "Expedition", "Expedition MAX", "Explorer", "F150 Regular Cab", "F150 Super Cab", "F150 SuperCrew Cab", "F250 Super Duty Crew Cab", "F250 Super Duty Regular Cab", "F250 Super Duty Super Cab", "F350 Super Duty Crew Cab", "F350 Super Duty Regular Cab", "F350 Super Duty Super Cab", "F450 Super Duty Crew Cab", "Fiesta", "Flex", "Focus", "Fusion", "Fusion Energi", "Mustang", "Ranger SuperCab", "Ranger SuperCrew", "Taurus", "Transit 150 Van", "Transit 150 Wagon", "Transit 250 Van", "Transit 350 HD Van", "Transit 350 Van", "Transit 350 Wagon", "Transit Connect Cargo", "Transit Connect Passenger")
        var freightlinerModels = arrayListOf("Sprinter 2500 Cargo", "Sprinter 2500 Crew", "Sprinter 2500 Passenger", "Sprinter 3500 Cargo", "Sprinter 3500XD Cargo", "Sprinter WORKER Cargo")
        var genesisModels = arrayListOf("G70", "G80", "G90")
        var gmcModels = arrayListOf("Acadia", "Canyon Crew Cab", "Canyon Extended Cab", "Savana 2500 Cargo", "Savana 2500 Passenger", "Savana 3500 Cargo", "Savana 3500 Passenger", "Sierra 1500 Crew Cab", "Sierra 1500 Double Cab", "Sierra 1500 Regular Cab", "Sierra 2500 HD Crew Cab", "Sierra 2500 HD Double Cab", "Sierra 2500 HD Regular Cab", "Sierra 3500 HD Crew Cab", "Sierra 3500 HD Double Cab", "Sierra 3500 HD Regular Cab", "Terrain", "Yukon", "Yukon XL")
        var hondaModels = arrayListOf("Accord", "Accord Hybrid", "Civic", "Civic Type R", "Clarity Electric", "Clarity Fuel Cell", "Clarity Plug-in Hybrid", "CR-V", "Fit", "HR-V", "Insight", "Odyssey", "Passport", "Pilot", "Ridgeline")
        var hyundaiModels = arrayListOf("Accent", "Elantra", "Elantra GT", "Ioniq Electric", "Ioniq Hybrid", "Ioniq Plug-in Hybrid", "Kona", "Kona Electric", "NEXO", "Santa Fe", "Santa Fe Sport", "Santa Fe XL", "Sonata", "Sonata Hybrid", "Sonata Plug-in Hybrid", "Tucson", "Veloster")
        var infinitiModels = arrayListOf("Q50", "Q60", "Q70", "QX30", "QX50", "QX60", "QX80")
        var jaguarModels = arrayListOf("E-PACE", "F-PACE", "F-TYPE", "I-PACE", "XE", "XF", "XJ")
        var jeepModels = arrayListOf("Cherokee", "Compass", "Grand Cherokee", "Renegade", "Wrangler", "Wrangler Unlimited")
        var kiaModels = arrayListOf("Cadenza", "Forte", "Forte5", "K900", "Niro", "Niro Plug-in Hybrid", "Optima", "Optima Hybrid", "Optima Plug-in Hybrid", "Rio", "Sedona", "Sorento", "Soul", "Soul EV", "Sportage", "Stinger", "Telluride")
        var lamborghiniModels = arrayListOf("Aventador", "Huracan")
        var landRoverModels = arrayListOf("Discovery", "Discovery Sport", "Range Rover", "Range Rover Evoque", "Range Rover Sport", "Range Rover Velar")
        var lexusModels = arrayListOf("ES", "GS", "GX", "IS", "LC", "LS", "LX", "NX", "RC", "RX", "UX")
        var lincolnModels = arrayListOf("Aviator", "Continental", "MKC", "MKT", "MKX", "MKZ", "Nautilus", "Navigator", "Navigator L")
        var lotusModels = arrayListOf("Evora 400")
        var maseratiModels = arrayListOf("Ghibli", "GranTurismo", "Levante", "Quattroporte")
        var mazdaModels = arrayListOf("CX-3", "CX-5", "CX-9", "MAZDA3", "MAZDA6", "MX-5 Miata", "MX-5 Miata RF")
        var mclarenModels = arrayListOf("570GT", "570S", "720S")
        var mercedesbenzModels = arrayListOf("A-Class", "C-Class", "CLA", "CLS", "E-Class", "G-Class", "GLA", "GLC", "GLC Coupe", "GLE", "GLS", "Mercedes-AMG C-Class", "Mercedes-AMG CLA", "Mercedes-AMG CLS", "Mercedes-AMG E-Class", "Mercedes-AMG G-Class", "Mercedes-AMG GLA", "Mercedes-AMG GLC", "Mercedes-AMG GLC Coupe", "Mercedes-AMG GLE", "Mercedes-AMG GLE Coupe", "Mercedes-AMG GLS", "Mercedes-AMG GT", "Mercedes-AMG S-Class", "Mercedes-AMG SL", "Mercedes-AMG SLC", "Mercedes-Maybach S-Class", "Metris Cargo", "Metris Passenger", "Metris WORKER Cargo", "Metris WORKER Passenger", "S-Class", "SL", "SLC", "Sprinter 2500 Cargo", "Sprinter 2500 Crew", "Sprinter 2500 Passenger", "Sprinter 3500 Cargo", "Sprinter 3500 XD Cargo", "Sprinter WORKER Cargo")
        var miniModels = arrayListOf("Clubman", "Convertible", "Countryman", "Hardtop 2 Door", "Hardtop 4 Door")
        var mitsubishiModels = arrayListOf("Eclipse Cross", "Mirage", "Mirage G4", "Outlander", "Outlander PHEV", "Outlander Sport")
        var nissanModels = arrayListOf("370Z", "Altima", "Armada", "Frontier Crew Cab", "Frontier King Cab", "GT-R", "Kicks", "LEAF", "Maxima", "Murano", "NV1500 Cargo", "NV200", "NV2500 HD Cargo", "NV3500 HD Cargo", "NV3500 HD Passenger", "Pathfinder", "Rogue", "Rogue Sport", "Sentra", "Titan Crew Cab", "Titan King Cab", "TITAN Single Cab", "TITAN XD Crew Cab", "TITAN XD King Cab", "TITAN XD Single Cab", "Versa", "Versa Note")
        var porscheModels = arrayListOf("718 Boxster", "718 Cayman", "911", "Cayenne", "Macan", "Panamera")
        var ramModels = arrayListOf("1500 Classic Crew Cab", "1500 Classic Quad Cab", "1500 Classic Regular Cab", "1500 Crew Cab", "1500 Quad Cab", "1500 Regular Cab", "2500 Crew Cab", "2500 Mega Cab", "2500 Regular Cab", "3500 Crew Cab", "3500 Mega Cab", "3500 Regular Cab", "ProMaster Cargo Van", "ProMaster City", "ProMaster Window Van")
        var rollsroyceModels = arrayListOf("Dawn", "Ghost", "Phantom", "Wraith")
        var smartModels = arrayListOf("fortwo electric drive", "fortwo electric drive cabrio")
        var subaruModels = arrayListOf("Ascent", "BRZ", "Crosstrek", "Forester", "Impreza", "Legacy", "Outback", "WRX")
        var testlaModels = arrayListOf("Model 3", "Model S", "Model X", "Model Y", "Roadster")
        var toyotaModels = arrayListOf("4Runner", "86", "Avalon", "Avalon Hybrid", "Camry", "Camry Hybrid", "C-HR", "Corolla", "Corolla Hatchback", "Corolla iM", "Highlander", "Highlander Hybrid", "Land Cruiser", "Mirai", "Prius", "Prius c", "Prius Prime", "RAV4", "RAV4 Hybrid", "Sequoia", "Sienna", "Tacoma Access Cab", "Tacoma Double Cab", "Tundra CrewMax", "Tundra Double Cab", "Yaris", "Yaris iA")
        var volkswagenModels = arrayListOf("Atlas", "Beetle", "e-Golf", "Golf", "Golf Alltrack", "Golf GTI", "Golf R", "Golf SportWagen", "Jetta", "Passat", "Tiguan", "Tiguan Limited")
        var volvoModels = arrayListOf("S60", "S90", "V60", "V90", "XC40", "XC60", "XC90")

        var fromVehicleView = false
    }
    private val db = FirebaseFirestore.getInstance()
    private val currentFirebaseUser = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_vehicle_info)

        var backButton = findViewById<Button>(R.id.skipButton)
        if(fromVehicleView){
            backButton.text = "BACK"
            backButton.setOnClickListener {
                fromVehicleView = true
                val i = Intent(this@VehicleInfoActivity, VehicleViewActivity::class.java)
                startActivity(i)
            }
        }
        else{
            backButton.setOnClickListener {
                skipButton()
            }
        }

        //*****************Car Make Spinner******************   **//
        val carMake = arrayListOf("Select a Car Make","Acura",
            "Alfa Romeo",
            "Aston Martin",
            "Audi",
            "Bentley",
            "BMW",
            "Buick",
            "Cadillac",
            "Chevrolet",
            "Chrysler",
            "Dodge",
            "Ferrari",
            "FIAT",
            "Ford",
            "Freightliner",
            "Genesis",
            "GMC",
            "Honda",
            "Hyundai",
            "INFINITI",
            "Jaguar",
            "Jeep",
            "Kia",
            "Lamborghini",
            "Land Rover",
            "Lexus",
            "Lincoln",
            "Lotus",
            "Maserati",
            "MAZDA",
            "McLaren",
            "Mercedes-Benz",
            "MINI",
            "Mitsubishi",
            "Nissan",
            "Porsche",
            "Ram",
            "Rolls-Royce",
            "smart",
            "Subaru",
            "Tesla",
            "Toyota",
            "Volkswagen",
            "Volvo")
        val carMakeSpinner: Spinner = findViewById(R.id.spinner)

        val acuraAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, acuraModel) {}
        val alfaRomeoAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, alfaRomeoModel){}
        val astonMartinAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, astonMartinModels) {}
        val audiAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, audiModels){}
        val bentleyAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, bentleyModels) {}
        val bmwAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, bmwModels){}
        val buickAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, buickModels) {}
        val cadillacAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, cadillacModels){}
        val chevroletAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, chevroletModels) {}
        val chryslerAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, chryslerModels){}
        val dodgeAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, dodgeModels) {}
        val ferrariAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, ferrariModels){}
        val fiatAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, fiatModels) {}
        val fordAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, fordModels){}
        val freightlinerAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, freightlinerModels) {}
        val genesisAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, genesisModels){}
        val gmcAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, gmcModels) {}
        val hondaAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, hondaModels){}
        val hyundaiAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, hyundaiModels) {}
        val infinitiAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, infinitiModels){}
        val jaguarAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, jaguarModels) {}
        val jeepAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, jeepModels){}
        val kiaAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, kiaModels) {}
        val lamborghiniAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, lamborghiniModels){}
        val landRoverAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, landRoverModels) {}
        val lexusAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, lexusModels){}
        val lincolnAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, lincolnModels) {}
        val lotusAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, lotusModels){}
        val maseratiAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, maseratiModels) {}
        val mazdaAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, mazdaModels){}
        val mclarenAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, mclarenModels) {}
        val mercedesbenzAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, mercedesbenzModels){}
        val miniAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, miniModels) {}
        val mitsubishiAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, mitsubishiModels){}
        val nissanAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, nissanModels) {}
        val porscheAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, porscheModels){}
        val ramAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, ramModels) {}
        val rollsroyceAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, rollsroyceModels){}
        val smartAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, smartModels) {}
        val subaruAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, subaruModels){}
        val teslaAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, testlaModels) {}
        val toyotaAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, toyotaModels){}
        val volkswagenAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, volkswagenModels) {}
        val volvoAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, volvoModels){}


        // Initializing an ArrayAdapter
        val carMakeDataAdapter = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, carMake) {
            override fun isEnabled(position:Int):(Boolean){
                return position != 0
            }

            override fun getDropDownView(position: Int, convertView: View?,
                                         parent: ViewGroup
            ): View? {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.BLACK)
                }
                return view
            }
        }
        carMakeDataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        carMakeSpinner.adapter = carMakeDataAdapter

        val carModelSpinner: Spinner = findViewById(R.id.spinner2)
        carMakeSpinner.onItemSelectedListener = object: (AdapterView.OnItemSelectedListener){
            override fun onItemSelected(parent : AdapterView<*>, view: View ,position:Int, id:Long) {
                var selectedItemText =  parent.getItemAtPosition(position)
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
//                    Toast.makeText(applicationContext, "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
//                        .show()
                    if(position == 1){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = acuraAdapter
                    }
                    else if(position == 2){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = alfaRomeoAdapter
                    }
                    else if(position == 3){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = astonMartinAdapter
                    }
                    else if(position == 4){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = audiAdapter
                    }
                    else if(position == 5){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = bentleyAdapter
                    }
                    else if(position == 6){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = bmwAdapter
                    }
                    else if(position == 7){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = buickAdapter
                    }
                    else if(position == 8){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = cadillacAdapter
                    }
                    else if(position == 9){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = chevroletAdapter
                    }
                    else if(position == 10){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = chryslerAdapter
                    }
                    else if(position == 11){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = dodgeAdapter
                    }
                    else if(position == 12){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = ferrariAdapter
                    }
                    else if(position == 13){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = fiatAdapter
                    }
                    else if(position == 14){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = fordAdapter
                    }
                    else if(position == 15){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = freightlinerAdapter
                    }
                    else if(position == 16){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = genesisAdapter
                    }
                    else if(position == 17){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = gmcAdapter
                    }
                    else if(position == 18){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = hondaAdapter
                    }
                    else if(position == 19){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = hyundaiAdapter
                    }
                    else if(position == 20){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = infinitiAdapter
                    }
                    else if(position == 21){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = jaguarAdapter
                    }
                    else if(position == 22){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = jeepAdapter
                    }
                    else if(position == 23){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = kiaAdapter
                    }
                    else if(position == 24){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = lamborghiniAdapter
                    }
                    else if(position == 25){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = landRoverAdapter
                    }
                    else if(position == 26){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = lexusAdapter
                    }
                    else if(position == 27){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = lincolnAdapter
                    }
                    else if(position == 28){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = lotusAdapter
                    }
                    else if(position == 29){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = maseratiAdapter
                    }
                    else if(position == 30){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = mazdaAdapter
                    }
                    else if(position == 31){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = mclarenAdapter
                    }
                    else if(position == 32){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = mercedesbenzAdapter
                    }
                    else if(position == 33){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = miniAdapter
                    }
                    else if(position == 34){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = mitsubishiAdapter
                    }
                    else if(position == 35){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = nissanAdapter
                    }
                    else if(position == 36){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = porscheAdapter
                    }
                    else if(position == 37){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = ramAdapter
                    }
                    else if(position == 38){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = rollsroyceAdapter
                    }
                    else if(position == 39){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = smartAdapter
                    }
                    else if(position == 40){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = subaruAdapter
                    }
                    else if(position == 41){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = teslaAdapter
                    }
                    else if(position == 42){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = toyotaAdapter
                    }
                    else if(position == 43){
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = volkswagenAdapter
                    }
                    else{
                        carModelSpinner.isClickable = true
                        carModelSpinner.adapter = volvoAdapter
                    }
                }
            }


            override fun onNothingSelected(parent:AdapterView<*>) {

            }
        }


        //*****************Car Model Spinner********************//
        val carModel = arrayListOf("Select a Car Model")
        val carModelDataAdapter = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, carModel) {}
        carModelDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        carModelSpinner.isClickable = false
        carModelSpinner.adapter = carModelDataAdapter


        //*****************Car Color Spinner********************//
        val carColor = arrayListOf("Select a Color","Aluminum", "Beige", "Black", "Blue",
            "Brown", "Bronze", "Claret", "Copper", "Cream", "Gold", "Gray", "Green",
            "Maroon", "Metallic", "Navy", "Orange", "Pink", "Purple", "Red", "Rose",
            "Rust", "Silver", "Tan", "Turquoise", "White", "Yellow")

        val carColorSpinner: Spinner = findViewById(R.id.spinner3)
        val carColorDataAdapter = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, carColor) {
            override fun isEnabled(position:Int):(Boolean){
                return position != 0
            }

            override fun getDropDownView(position: Int, convertView: View?,
                                         parent: ViewGroup
            ): View? {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.BLACK)
                }
                return view
            }
        }
        carColorDataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        carColorSpinner.adapter = carColorDataAdapter

        //*****************State Location Spinner********************//
        val carStates = arrayListOf("Select a State","Alabama",
            "Alaska",
            "Arizona",
            "Arkansas",
            "California",
            "Colorado",
            "Connecticut",
            "Delaware",
            "Florida",
            "Georgia",
            "Hawaii",
            "Idaho",
            "Illinois", "Indiana",
            "Iowa",
            "Kansas",
            "Kentucky",
            "Louisiana",
            "Maine",
            "Maryland",
            "Massachusetts",
            "Michigan",
            "Minnesota",
            "Mississippi",
            "Missouri",
            "Montana", "Nebraska",
            "Nevada",
            "New Hampshire",
            "New Jersey",
            "New Mexico",
            "New York",
            "North Carolina",
            "North Dakota",
            "Ohio",
            "Oklahoma",
            "Oregon",
            "Pennsylvania", "Rhode Island",
            "South Carolina",
            "South Dakota",
            "Tennessee",
            "Texas",
            "Utah",
            "Vermont",
            "Virginia",
            "Washington",
            "West Virginia",
            "Wisconsin",
            "Wyoming")

        val carStateSpinner: Spinner = findViewById(R.id.spinner4)
        val carStateDataAdapter = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, carStates) {
            override fun isEnabled(position:Int):(Boolean){
                return position != 0
            }

            override fun getDropDownView(position: Int, convertView: View?,
                                         parent: ViewGroup
            ): View? {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.BLACK)
                }
                return view
            }
        }
        carStateDataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        carStateSpinner.adapter = carStateDataAdapter
    }

    private fun saveVehicleInfo(v :View){
        val editText = findViewById<EditText>(R.id.edit_license)
        val value = editText.text.toString().toUpperCase()
        var carArray: Any?
        var carInfo :MutableList<String>

        val carMakeSpinner: Spinner = findViewById(R.id.spinner)
        val carModelSpinner: Spinner = findViewById(R.id.spinner2)
        val carColorSpinner: Spinner = findViewById(R.id.spinner3)
        val carStateSpinner: Spinner = findViewById(R.id.spinner4)


        if(spinner.selectedItem.toString() == "Select a Car Make" || spinner2.selectedItem == "Select a Car Model"
            || spinner3.selectedItem == "Select a Car Color" || spinner4.selectedItem == "Select a State" || value == ""){
            Toast.makeText(applicationContext, "Please fill out all information", Toast.LENGTH_LONG)
                .show()
        }
        else {
            if(!VehicleInfoActivity.fromVehicleView) {
                val extras = intent.extras
                val fullName = extras.getString("fullName")
                val email = extras.getString("email")
                val dateofBirth = extras.getString("dateOfBirth")
                Log.d(TAG,"HELLO WORLD::::")
                val users = HashMap<String, Any?>()
                users["fullName"]= fullName
                users["email"]= email
                users["dateOfBirth"]= dateofBirth
                users["phoneNumber"]= currentFirebaseUser!!.phoneNumber
                users["Cars"]= arrayListOf(carMakeSpinner.selectedItem.toString(),carModelSpinner.selectedItem.toString(),carColorSpinner.selectedItem.toString(),carStateSpinner.selectedItem.toString(),value)
                users["Addresses"] = arrayListOf<String>()
                users["Reservations"] = arrayListOf<String>()

                db.collection("Users").document(currentFirebaseUser!!.uid)
                    .set(users)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: HELLLLLLLLLLO")
                        val i = Intent(this@VehicleInfoActivity, NavigationActivity::class.java)
                        startActivity(i)
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            }
            else {
                Log.d(TAG,"HELLLO WORLD::::")
                val docRef = db.collection("Users").document(currentFirebaseUser!!.uid)
                docRef.get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            carArray = document.data!!["Cars"]
                            Log.d(VehicleViewActivity.TAG, "DocumentSnapshot dataaaa: " + carArray.toString())
                            carInfo = carArray as MutableList<String>
                            carInfo.add(carMakeSpinner.selectedItem.toString())
                            carInfo.add(carModelSpinner.selectedItem.toString())
                            carInfo.add(carColorSpinner.selectedItem.toString())
                            carInfo.add(carStateSpinner.selectedItem.toString())
                            carInfo.add(value)

                            db.collection("Users").document(currentFirebaseUser.uid)
                                .update("Cars", carInfo)
                                .addOnSuccessListener { documentReference ->
                                    if(VehicleInfoActivity.fromVehicleView) {
                                        VehicleInfoActivity.fromVehicleView = false
                                        VehicleViewActivity.fromVehicleInfoPage = true
                                        val i = Intent(this@VehicleInfoActivity, VehicleViewActivity::class.java)
                                        startActivity(i)
                                    }
                                    else{
                                        val i = Intent(this@VehicleInfoActivity, NavigationActivity::class.java)
                                        startActivity(i)
                                    }
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding document", e)
                                }
                        } else {
                            Log.d(VehicleViewActivity.TAG, "No such document")

                        }
                    }
            }
        }
    }

    fun clickButton(v : View){
        saveVehicleInfo(v)
    }

    private fun skipButton(){
        val extras = intent.extras
        val fullName = extras.getString("fullName")
        val email = extras.getString("email")
        val dateofBirth = extras.getString("dateOfBirth")
        Log.d(TAG,"HELLO WORLD::::")
        val users = HashMap<String, Any?>()
        users["fullName"]= fullName
        users["email"]= email
        users["dateOfBirth"]= dateofBirth
        users["phoneNumber"]= currentFirebaseUser!!.phoneNumber
        users["Cars"]= arrayListOf<String>()
        users["Addresses"] = arrayListOf<String>()
        users["Reservations"] = arrayListOf<String>()
        db.collection("Users").document(currentFirebaseUser.uid)
            .set(users)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: HELLLLLLLLLLO")
                val i = Intent(this@VehicleInfoActivity, NavigationActivity::class.java)
                startActivity(i)
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

        val i = Intent(this@VehicleInfoActivity, NavigationActivity::class.java)
        startActivity(i)
    }
}