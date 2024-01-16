<%-- 
    Document   : RequestDelivery_Form
    Created on : 12-Jul-2023, 10:32:57
    Author     : kenne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Send a package</title>     
        <!-- Misc -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://www.paypal.com/sdk/js?client-id=AWEcy8P1vljB2CPzqAaI2zFv_F2P4bEEoX7Z5qbft8X6W9DEEwmWyzLJE87NALHjnDw1ZWB3O6UB8YqK&locale=en_US"></script>       

        <!-- Hyperlink -->
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/payment.css">
        <script type = "text/javascript" src="<%= request.getContextPath()%>/javascript/requestDelivery.js"></script>
        <script type = "text/javascript" src ="<%= request.getContextPath()%>/javascript/payment.js"></script>
     

    </head> 

    <body>
        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="col-11 col-sm-9 col-md-7 col-lg-6 col-xl-5 text-center p-0 mt-3 mb-2">
                    <div class="card px-0 pt-4 pb-0 mt-3 mb-3">
                        <h2 id="heading">Request Delivery</h2>
                        <p>Fill all form field to go to next step</p>
                        <form id="msform">
                            <!-- Progress bar -->
                            <ul id="progressbar">
                                <li class="active" id="account"><strong>Recipient</strong></li>
                                <li id="personal" name="personal"><strong>Personal</strong></li>
                                <li id="package"><strong>Package</strong></li>
                                <li id="offers"><strong>Offers</strong></li>
                                <li id="payment"><strong>Payment</strong></li>
                            </ul>
                            <div class="progress">
                                <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
                            </div> 
                            <br> 
                            <!-- fieldsets -->
                            <fieldset>
                                <div class="form-card">

                                    <div class="row">
                                        <div class="col-7">
                                            <h2 class="fs-title" id="personal">Recipient Information:</h2>
                                        </div>
                                        <div class="col-5">
                                            <h2 class="steps">Step 1 - 5</h2>
                                        </div>
                                    </div> 

                                    <label class="fieldlabels">Recipient Name</label> 
                                    <input type="text" id="recipient" name="recipient" placeholder="Example: John Smith" required> 

                                    <label class="fieldlabels">Phone no:</label> 
                                    <input type="text" id="r_phone" name="r_phone" placeholder="Example: 010-1112233" required>

                                    <label class="fieldlabels">Delivery Address: </label> 
                                    <input type="text" id="r_address" name="r_address" placeholder="Example: Street Address" required>

                                    <label for="district">City/Township:</label>
                                    <select id="r_district" id="r_district" name="r_district" required>
                                        <option value="" disabled selected>Select a township</option>
                                        <option value="JohorBahru">Johor Bahru</option>
                                        <option value="Jelutong">Jelutong</option>
                                        <option value="Plentong">Plentong</option>
                                        <option value="Pulai">Pulai</option>
                                        <option value="SungaiTiram">Sungai Tiram</option>
                                        <option value="TanjungKupang">Tanjung Kupang</option>
                                        <option value="Tebrau1">Tebrau (Mukim Tebrau)</option>
                                        <option value="Tebrau2">Tebrau (Bandar Tebrau)</option>
                                    </select>
                                </div> 
                                
                                <input type="button" name="next" class="next action-button" value="Next" />
                            </fieldset>

                            <fieldset>
                                <div class="form-card">

                                    <div class="row">
                                        <div class="col-7">
                                            <h2 class="fs-title">Personal Information:</h2>
                                        </div>
                                        <div class="col-5">
                                            <h2 class="steps">Step 2 - 5</h2>
                                        </div>
                                    </div> 

                                    <label class="fieldlabels">Name: </label> 
                                    <input type="text" id="name" name="name" value="<%= session.getAttribute("myName")%>" readonly="true"/> 

                                    <label class="fieldlabels">Phone: </label> 
                                    <input type="text" id="phone" name="phone" value="<%= session.getAttribute("myPhone")%>" readonly="true"/> 

                                    <label class="fieldlabels">Address: </label> 
                                    <input type="text" id="address" name="address" value="<%= session.getAttribute("myAddress")%>" readonly="true">                        
                                </div> 
                                <input type="button" name="next" class="next action-button" value="Next" /> 
                                <input type="button" name="previous" class="previous action-button-previous" value="Previous" />    
                            </fieldset>

                            <fieldset>
                                <div class="form-card">
                                    <div class="row">
                                        <div class="col-7" >
                                            <h2 class="fs-title">Package Information: </h2>
                                        </div>
                                        <div class="col-5">
                                            <h2 class="steps">Step 3 - 5</h2>
                                        </div>
                                    </div> 
                                    <div class="checkboxes">
                                        <label class="fieldlabels">Package Type </label> 
                                        <select name="packageWeight" id="packageType" required>
                                            <option value ="" disabled selected>Select a category</option>
                                            <option value ="food">Food</option>
                                            <option value ="toys">Toys & Games</option>
                                            <option value ="accessories">accessories</option>
                                            <option value ="others">others</option>
                                        </select> 
                                        <br>
                                        <label class="fieldlabels">Package Weight </label> 
                                        <select name="packageWeight" id="packageWeight" required>
                                            <option value ="" disabled selected>Select Weight&#160;&#160;&#160;&#160;&#160;</option>
                                            <option value ="lessthan1kg">< 1kg</option>
                                            <option value ="1kg-10kg">1kg ~ 10kg</option>
                                            <option value ="11kg-30kg">11kg ~ 30kg</option>
                                            <option value ="31kg-50kg">31kg ~ 50kg</option>
                                            <option value ="morethan50kg">> 50kg</option>
                                        </select> 
                                        <br>
                                        <label class="fieldlabels">Package Quantity </label> <i class="icon fa fa-exclamation-circle" aria-hidden="true" title="ITEMS inside parcel, not parcel quantity!"></i>
                                        <select name="packageWeight" id="packageQuantity" required>
                                            <option value ="" disabled selected>Select a number&#160;</option>
                                            <option value ="1">1</option>
                                            <option value ="2">2</option>
                                            <option value ="3">3</option>
                                            <option value ="4">4</option>
                                            <option value ="5">5</option>
                                        </select> 
                                        <br><br>

                                        <h2 class="fs-title">Optional add-Ons: </h2>      
                                        <p></p>
                                        <label class="fieldlabels" title="Delivery on the same or the next day!"/> Express 
                                        <input type="checkbox" id="express" name="express" value="express"/> 
                                        </label> <i class="icon fa fa-exclamation-circle" aria-hidden="true" title=""></i>
                                        <br>
                                        <label class="fieldlabels"/> Fragile 
                                        <input type="checkbox" id="fragile" name="fragile" value="fragile"/> 
                                        </label>
                                        <br>
                                        <label class="fieldlabels"/> Extra Packaging
                                        <input type="checkbox" id="exPackage" name="exPackage" value="exPackage"/> 
                                        </label>
                                    </div>
                                    <p></p>
                                    <label class="fieldlabels">Upload Your Package Photo:</label> <i class="fa fa-question-circle" aria-hidden="true" title="Upload your parcel contents to apply for refund policy if an accident occurred!"></i>
                                    <input type="file" id="pic" name="pic" accept="image/*"> 
                                    <canvas id="prevPic" hidden="true" onchange="handleFileUpload()"> </canvas>

                                </div> 
                                <input type="button" name="next" class="next action-button" value="Next" /> 
                                <input type="button" name="previous" class="previous action-button-previous" value="Previous" />
                            </fieldset>

                            <fieldset>
                                <div class="form-card">
                                    <div class="row">
                                        <div class="col-7">
                                            <h2 class="fs-title">Offers: </h2>
                                        </div>
                                        <div class="col-5">
                                            <h2 class="steps">Step 4 - 5</h2>
                                        </div>
                                    </div> 
                                    
                                        <div class="price-row"> 
                                            <div class="price-col" id="standard-col" onclick="selectModel()"> 
                                                <div id="standard-title"><p style="color: white">Standard</p></div>            
                                                <hr> 
                                                <h3>$ 4.9/<span>mo</span></h3> 
                                                <ul> 
                                                    <li>Upgrade Priority Status</li> 
                                                    <li>Ads Free</li> 
                                                    <li>Priority email support</li> 
                                                    <li>Help center access</li> 
                                                </ul> 
                                                <input type="radio" id="standard" name="offer" value="standard">
                                            </div> 
                                            
                                            <div class="price-col2" id="best" onclick="selectModel()"> 
                                                <p id="prem">Premium*</p> 
                                                <hr> 
                                                <h3>$ 9.9/<span>mo</span></h3> 
                                                <ul> 
                                                    <li>All Standard Features</li> 
                                                    <li>Data Algorithms</li> 
                                                    <li>Phone assistance</li> 
                                                    <li>Free of charge add-Ons</li> 
                                                </ul> 
                                                <input type="radio" id="premium" name="offer" value="premium">
                                            </div> 
                                        </div>        
                                </div> 
                                <p></p>
                                <div id ="nopeOffer">
                                    <label>No thank you!
                                        <input type="radio" id="noOffer" name="offer" value="noOffer" style="position:absolute; right: 10%; bottom:15%;" onclick="selectModel()">
                                    </label>
                                </div>
                                <p></p>
                                <input type="button" name="next" class="next action-button" value="Next" /> 
                                <input type="button" name="previous" class="previous action-button-previous" value="Previous" />
                            </fieldset>

                            <fieldset>
                                <div class="form-card">
                                    <div class="row">
                                        <div class="col-7">
                                            <h2 class="fs-title">Checkout:</h2>
                                        </div>
                                        <div class="col-5">
                                            <h2 class="steps">Step 5 - 5</h2>
                                        </div>
                                    </div> <br><br>
                                    <h2 class="purple-text text-center"><strong>Payment Method</strong></h2> <br>
                                    <label class="fieldlabels" id="totalPrice"/></label>
                                    <div class="row justify-content-center">
                                        <div id="paypal-button-container">
                                            <!--Email: sb-k1u9h26912348@personal.example.com -->  
                                            <!--Password: b5m?*w9B -->       
                                        </div>

                                    </div> 
                                    <br><br>
                                    <div class="row justify-content-center">
                                        <div class="col-7 text-center">
                                            <h5 class="purple-text text-center"></h5>
                                        </div>
                                    </div>
                                </div>
                                <!--  <input type="submit" name="submit" class="action-button" value="Submit" formaction="FunctionServlet"> -->
                                <input type="button" name="previous" class="previous action-button-previous" value="Previous" />
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div> 
    </body>

</html>                            