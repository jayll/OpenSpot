package com.example.openspot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.preference.Preference
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.startActivity
import android.view.ViewGroup
import android.widget.*
import android.widget.EditText
import com.google.common.primitives.UnsignedBytes.toInt
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_vehicle_info.*
import android.content.Context.MODE_PRIVATE





class
EditVehicleInfoActivity : AppCompatActivity(){

    companion object {
        const val TAG = "VehicleInfoActivity"
        var fromEditVehiclePage = false
    }
    private val db = FirebaseFirestore.getInstance()
    private val currentFirebaseUser = FirebaseAuth.getInstance().currentUser

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            setTheme(R.style.AppTheme)
            setContentView(R.layout.activity_edit_vehicle_info)

            val carMakeSpinner: Spinner = findViewById(R.id.spinner)
            val carModelSpinner: Spinner = findViewById(R.id.spinner2)
            val carColorSpinner: Spinner = findViewById(R.id.spinner3)
            val carStateSpinner: Spinner = findViewById(R.id.spinner4)

            val extras = intent.extras
            var carMakeData :String = extras!!.getString("carMake")
            var carModelData:String? = extras!!.getString("carModel")
            var carColorData:String? = extras!!.getString("carColor")
            var carStateData:String? = extras!!.getString("carState")
            var carLicensePlate:String? = extras!!.getString("carLicense")
            var counterData:String? = extras!!.getString("counter")

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

            val acuraAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.acuraModel ){}
            val alfaRomeoAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.alfaRomeoModel){}
            val astonMartinAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.astonMartinModels) {}
            val audiAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.audiModels){}
            val bentleyAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.bentleyModels) {}
            val bmwAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.bmwModels){}
            val buickAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.buickModels) {}
            val cadillacAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.cadillacModels){}
            val chevroletAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.chevroletModels) {}
            val chryslerAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.chryslerModels){}
            val dodgeAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.dodgeModels) {}
            val ferrariAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.ferrariModels){}
            val fiatAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.fiatModels) {}
            val fordAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,VehicleInfoActivity.fordModels){}
            val freightlinerAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.freightlinerModels) {}
            val genesisAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.genesisModels){}
            val gmcAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.gmcModels) {}
            val hondaAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.hondaModels){}
            val hyundaiAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.hyundaiModels) {}
            val infinitiAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.infinitiModels){}
            val jaguarAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.jaguarModels) {}
            val jeepAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.jeepModels){}
            val kiaAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.kiaModels) {}
            val lamborghiniAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.lamborghiniModels){}
            val landRoverAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.landRoverModels) {}
            val lexusAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.lexusModels){}
            val lincolnAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.lincolnModels) {}
            val lotusAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.lotusModels){}
            val maseratiAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.maseratiModels) {}
            val mazdaAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.mazdaModels){}
            val mclarenAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.mclarenModels) {}
            val mercedesbenzAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.mercedesbenzModels){}
            val miniAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.miniModels) {}
            val mitsubishiAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.mitsubishiModels){}
            val nissanAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.nissanModels) {}
            val porscheAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.porscheModels){}
            val ramAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.ramModels) {}
            val rollsroyceAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.rollsroyceModels){}
            val smartAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.smartModels) {}
            val subaruAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.subaruModels){}
            val teslaAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.testlaModels) {}
            val toyotaAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.toyotaModels){}
            val volkswagenAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.volkswagenModels) {}
            val volvoAdapter = object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, VehicleInfoActivity.volvoModels){}


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

            carMakeSpinner.onItemSelectedListener = object: (AdapterView.OnItemSelectedListener){
                override fun onItemSelected(parent : AdapterView<*>, view: View ,position:Int, id:Long) {
                    var selectedItemText =  parent.getItemAtPosition(position)
                    // If user change the default selection
                    // First item is disable and it is used for hint
                    if(position > 0){
                        // Notify the selected item text
//                        Toast.makeText(applicationContext, "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
//                            .show()
                        if(position == 1){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = acuraAdapter
                            val carModelSpinnerPosition = acuraAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 2){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = alfaRomeoAdapter
                            val carModelSpinnerPosition = alfaRomeoAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 3){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = astonMartinAdapter
                            val carModelSpinnerPosition = astonMartinAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 4){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = audiAdapter
                            val carModelSpinnerPosition = audiAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 5){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = bentleyAdapter
                            val carModelSpinnerPosition = bentleyAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 6){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = bmwAdapter
                            val carModelSpinnerPosition = bmwAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 7){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = buickAdapter
                            val carModelSpinnerPosition = buickAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 8){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = cadillacAdapter
                            val carModelSpinnerPosition = cadillacAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 9){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = chevroletAdapter
                        }
                        else if(position == 10){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = chryslerAdapter
                            val carModelSpinnerPosition = chryslerAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 11){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = dodgeAdapter
                            val carModelSpinnerPosition = dodgeAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 12){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = ferrariAdapter
                            val carModelSpinnerPosition = ferrariAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 13){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = fiatAdapter
                            val carModelSpinnerPosition = fiatAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 14){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = fordAdapter
                            val carModelSpinnerPosition = fordAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 15){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = freightlinerAdapter
                            val carModelSpinnerPosition = freightlinerAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 16){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = genesisAdapter
                            val carModelSpinnerPosition = genesisAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 17){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = gmcAdapter
                            val carModelSpinnerPosition = gmcAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 18){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = hondaAdapter
                            val carModelSpinnerPosition = hondaAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 19){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = hyundaiAdapter
                            val carModelSpinnerPosition = hyundaiAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 20){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = infinitiAdapter
                            val carModelSpinnerPosition = infinitiAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 21){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = jaguarAdapter
                            val carModelSpinnerPosition = jaguarAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 22){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = jeepAdapter
                            val carModelSpinnerPosition = jeepAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 23){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = kiaAdapter
                            val carModelSpinnerPosition = kiaAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 24){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = lamborghiniAdapter
                            val carModelSpinnerPosition = lamborghiniAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 25){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = landRoverAdapter
                            val carModelSpinnerPosition = landRoverAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 26){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = lexusAdapter
                            val carModelSpinnerPosition = lexusAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 27){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = lincolnAdapter
                            val carModelSpinnerPosition = lincolnAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 28){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = lotusAdapter
                            val carModelSpinnerPosition = lotusAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 29){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = maseratiAdapter
                            val carModelSpinnerPosition = maseratiAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 30){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = mazdaAdapter
                            val carModelSpinnerPosition = mazdaAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 31){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = mclarenAdapter
                            val carModelSpinnerPosition = mclarenAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 32){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = mercedesbenzAdapter
                            val carModelSpinnerPosition = mercedesbenzAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 33){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = miniAdapter
                            val carModelSpinnerPosition = miniAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 34){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = mitsubishiAdapter
                            val carModelSpinnerPosition = mitsubishiAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 35){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = nissanAdapter
                            val carModelSpinnerPosition = nissanAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 36){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = porscheAdapter
                            val carModelSpinnerPosition = porscheAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 37){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = ramAdapter
                            val carModelSpinnerPosition = ramAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 38){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = rollsroyceAdapter
                            val carModelSpinnerPosition = rollsroyceAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 39){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = smartAdapter
                            val carModelSpinnerPosition = smartAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 40){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = subaruAdapter
                            val carModelSpinnerPosition = subaruAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 41){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = teslaAdapter
                            val carModelSpinnerPosition = teslaAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 42){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = toyotaAdapter
                            val carModelSpinnerPosition = toyotaAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else if(position == 43){
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = volkswagenAdapter
                            val carModelSpinnerPosition = volkswagenAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
                        }
                        else{
                            carModelSpinner.isClickable = true
                            carModelSpinner.adapter = volvoAdapter
                            val carModelSpinnerPosition = volvoAdapter.getPosition(carModelData)
                            carModelSpinner.setSelection(carModelSpinnerPosition)
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

//            carColorSpinner.onItemSelectedListener = object :  AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(parent : AdapterView<*>, view: View ,position:Int, id:Long) {
//                    var selectedItemText =  parent.getItemAtPosition(position)
//                    // If user change the default selection
//                    // First item is disable and it is used for hint
//                    if(position > 0) {
//                        // Notify the selected item text
//                        Toast.makeText(applicationContext, "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//            }
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

//            carStateSpinner.onItemSelectedListener = object :  AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(parent : AdapterView<*>, view: View ,position:Int, id:Long) {
//                    var selectedItemText =  parent.getItemAtPosition(position)
                    // If user change the default selection
                    // First item is disable and it is used for hint
//                    if(position > 0) {
//                        // Notify the selected item text
//                        Toast.makeText(applicationContext, "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//            }

        val carMakeSpinnerPosition = carMakeDataAdapter.getPosition(carMakeData)
        carMakeSpinner.setSelection(carMakeSpinnerPosition)

        val carColorSpinnerPosition = carColorDataAdapter.getPosition(carColorData)
        carColorSpinner.setSelection(carColorSpinnerPosition)

            val editText = findViewById<EditText>(R.id.edit_license)
            editText.setText(carLicensePlate!!.toUpperCase())

            val carStateSpinnerPosition = carStateDataAdapter.getPosition(carStateData)
            carStateSpinner.setSelection(carStateSpinnerPosition)
    }

    private fun saveVehicleInfo(v :View){
        val editText = findViewById<EditText>(R.id.edit_license)
        val value = editText.text.toString().toUpperCase()
        var carArray: Any?
        var name =""
        var carInfo :MutableList<String>
        val extras = intent.extras
        var counterData:String? = extras!!.getString("counter")
        var car2 = extras!!.getStringArrayList("a")
        var carNumber = counterData.toString()[counterData.toString().length-1].toInt()

        Log.d(VehicleViewActivity.TAG, "DocumentSnapshot dataaaaLOL: " + car2)

        val carMakeSpinner: Spinner = findViewById(R.id.spinner)
        val carModelSpinner: Spinner = findViewById(R.id.spinner2)
        val carColorSpinner: Spinner = findViewById(R.id.spinner3)
        val carStateSpinner: Spinner = findViewById(R.id.spinner4)

        val docRef = db.collection("Users").document(currentFirebaseUser?.uid.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    carArray = document.data!!["Cars"]
                    Log.d(VehicleViewActivity.TAG, "DocumentSnapshot dataaaa: " + carArray.toString())
                    carInfo = carArray as MutableList<String>
                    for(i in 0..4){
                        when(i) {
                            0 -> {
                                carInfo[((carNumber-48) * 5) + i] = carMakeSpinner.selectedItem.toString()
                                name += carMakeSpinner.selectedItem.toString()
                            }

                            1 -> {
                                carInfo[((carNumber - 48) * 5) + i] = carModelSpinner.selectedItem.toString()
                                name += " " +carModelSpinner.selectedItem.toString()
                            }
                            2 -> {
                                carInfo[((carNumber-48) * 5) + i] = carColorSpinner.selectedItem.toString()
                            }

                            3 -> {
                                carInfo[((carNumber-48) * 5) + i] = carStateSpinner.selectedItem.toString()
                            }

                            4 -> {
                                carInfo[((carNumber-48) * 5) + i] = value
                                name += "\n" + value
                                Preference(this).title = name
                            }
                        }
                    }

                    db.collection("Users").document(currentFirebaseUser?.uid.toString())
                        .update("Cars",carInfo)
                        .addOnSuccessListener { documentReference ->
                            //                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                            val intent = Intent(this@EditVehicleInfoActivity, VehicleViewActivity::class.java)
                            startActivity(intent)
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                } else {
                    Log.d(VehicleViewActivity.TAG, "No such document")

                }
            }
    }

    private fun deleteCarInfo(v:View){
        val extras = intent.extras
        var carArray: Any?
        var counterData:String? = extras!!.getString("counter")
        var carNumber = counterData.toString()[counterData.toString().length-1].toInt()
        var carInfo :MutableList<String>

        val docRef = db.collection("Users").document(currentFirebaseUser?.uid.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    carArray = document.data!!["Cars"]
                    Log.d(VehicleViewActivity.TAG, "DocumentSnapshot dataaaa: " + carArray.toString())
                    carInfo = carArray as MutableList<String>
                    for(i in 0..4){
                             carInfo.removeAt(((carNumber-48) * 5))
                    }

                    db.collection("Users").document(currentFirebaseUser?.uid.toString())
                        .update("Cars",carInfo)
                        .addOnSuccessListener { documentReference ->
                            val intent = Intent(this@EditVehicleInfoActivity, VehicleViewActivity::class.java)
                            startActivity(intent)
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                }
            else {
                    Log.d(VehicleViewActivity.TAG, "No such document")

                }
            }
    }

    fun clickButton(v : View){
        EditVehicleInfoActivity.fromEditVehiclePage = true
        saveVehicleInfo(v)
    }

    fun deleteButton(v:View){
        deleteCarInfo(v)
    }
}
