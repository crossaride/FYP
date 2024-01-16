<%-- 
    Document   : homepage
    Created on : Dec 12, 2022, 9:08:14 AM
    Author     : kenneth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"> 
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Misc -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

        <!-- Title -->
        <title>Freight Management</title>

        <!-- Hyperlinks -->       
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/style.css"> 
        <script type = "text/javascript" src="<%= request.getContextPath()%>/javascript/homepage.js"></script>  
    </head>

    <body>
        <div class="main"> 
            <!-- Top Nav bar -->
            <div id = "navb" class="navbar fixed-top">
                <img src = ".../_mat/logo.PNG" class="logo">
                <!-- link to <sections> -->
                <ul>
                    <li><a href = "#">Home</a></li>
                    <li><a href = "#about">About Us</a></li>
                    <li><a href = "#service">Services</a></li>
                    <li><a href = "#offer">Offers</a></li>
                    <li><a href = "#contact">Contact Us</a></li>
                </ul>        
                <button class="signin" type="button" onclick="location.href = 'login.jsp';">Sign in</button>
            </div>
        </div>

        <!-- Section -->
        <div id="home"> 
            <div class="content">
                <h1>We ship your package with care</h1>
                <p>Only available in Johor Bahru</p>
                <div class="button">
                    <button class="getstarted" type ="button" onclick="location.href = 'login.jsp';"><span>Get Started</span></button>
                </div>
            </div>
        </div>

        <!-- Section2 -->
        <div id="about">
            <div class="bg-light">
                <div class="container py-5">
                    <div class="row h-100 align-items-center py-5">
                        <div class="col-lg-6">
                            <h2 class="display-4">About Us!</h2><br>
                            <p class="lead text-muted mb-0" align="justify">
                                FMS was built as a FYP project, created by a University student. <br><br> 
                                FMS aims to help the small-to-mid companies who started their business on pandemic and have no clue on how to manage a system, ex. database management. FMS simplifies the process and helps in Inventory Management, and Delivery Management, including database storage, manage accounts, best route calculation, schedule management, and more.</p>
                        </div>
                        <div class="col-lg-6 d-none d-lg-block"><img src="https://bootstrapious.com/i/snippets/sn-about/illus.png" alt="" class="img-fluid"></div>
                    </div>
                </div>
            </div>

            <!-- Section3 -->
            <div class="bg-light py-5">
                <div class="container py-5">
                    <div class="row mb-4">
                        <div class="col-lg-5">
                            <h2 class="display-4 font-weight-light">Our team</h2>
                        </div>
                    </div>

                    <div class="row text-center">
                        <div class="col-xl-3 col-sm-6 mb-5">
                            <div class="bg-white rounded shadow-sm py-5 px-4"><img src="https://bootstrapious.com/i/snippets/sn-about/avatar-4.png" alt="" width="100" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
                                <h5 class="mb-0">Kenneth Lai</h5><span class="small text-uppercase text-muted">Lead Developer</span>
                                <ul class="social mb-0 list-inline mt-3">
                                    <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-facebook-f"></i></a></li>
                                    <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-twitter"></i></a></li>
                                    <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-instagram"></i></a></li>
                                    <li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-linkedin"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Section4 -->
        <div id="service">
            <div class="container marketing">
                <br><br><br><br>
                <div class="pricing-header p-3 pb-md-4 mx-auto text-center">
                    <h2 class="display-4 fw-normal">Services</h2>
                </div>

                <hr class="featurette-divider"> <!-- divider of section -->
                <br> 

                <!-- PLACEHOLDERS for image and stuff -->
                <div class="row featurette">
                    <div class="col-md-7">
                        <h2 class="featurette-heading fw-normal lh-1">First featurette heading. <span class="text-muted">It’ll blow your mind.</span></h2>
                        <p class="lead">Some great placeholder content for the first featurette here. Imagine some exciting prose here.</p>
                    </div>
                    <div class="col-md-5">
                        <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#eee"/><text x="50%" y="50%" fill="#aaa" dy=".3em">500x500</text></svg>
                    </div>
                </div>

                <br><br>
                <hr class="featurette-divider"> <!-- divider of section -->
                <br><br>

                <div class="row featurette">
                    <div class="col-md-7 order-md-2">
                        <h2 class="featurette-heading fw-normal lh-1">Oh yeah, it’s that good. <span class="text-muted">See for yourself.</span></h2>
                        <p class="lead">Another featurette? Of course. More placeholder content here to give you an idea of how this layout would work with some actual real-world content in place.</p>
                    </div>
                    <div class="col-md-5 order-md-1">
                        <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#eee"/><text x="50%" y="50%" fill="#aaa" dy=".3em">500x500</text></svg>
                    </div>
                </div>

                <br><br>
                <hr class="featurette-divider"> <!-- divider of section -->
                <br><br>

                <div class="row featurette">
                    <div class="col-md-7">
                        <h2 class="featurette-heading fw-normal lh-1">And lastly, this one. <span class="text-muted">Checkmate.</span></h2>
                        <p class="lead">And yes, this is the last block of representative placeholder content. Again, not really intended to be actually read, simply here to give you a better view of what this would look like with some actual content. Your content.</p>
                    </div>
                    <div class="col-md-5">
                        <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#eee"/><text x="50%" y="50%" fill="#aaa" dy=".3em">500x500</text></svg>
                    </div>
                </div>

                <br><br>
                <hr class="featurette-divider"> <!-- divider of section -->
                <br><br> 
            </div>
        </div>

        <!-- Section5 -->
        <div id="offer">
            <br><br><br><p></p>
            <main class="container-carousell">
                <div class="pricing-header p-3 pb-md-4 mx-auto text-center">
                    <h2 class="display-4 fw-normal">Pricing</h2>
                    <p class="fs-5 text-muted">Quickly build an effective pricing table for your potential customers with this Bootstrap example. It’s built with default Bootstrap components and utilities with little customization.</p>
                </div>

                <div class="row row-cols-1 row-cols-md-3 mb-3 text-center">

                    <div class="col">
                        <div class="card mb-4 rounded-3 shadow-sm">
                            <div class="card-header py-3">
                                <h4 class="my-0 fw-normal">Free</h4>
                            </div>
                            <div class="card-body">
                                <h1 class="card-title pricing-card-title">$0<small class="text-muted fw-light">/mo</small></h1>
                                <ul class="list-unstyled mt-3 mb-4">
                                    <li>Free access to the system</li>
                                    <li>Receive Ads by email</li>
                                    <li>Email support</li>
                                    <li>Help center access</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="col">
                        <div class="card mb-4 rounded-3 shadow-sm">
                            <div class="card-header py-3">
                                <h4 class="my-0 fw-normal">Standard</h4>
                            </div>
                            <div class="card-body">
                                <h1 class="card-title pricing-card-title">$4.9<small class="text-muted fw-light">/mo</small></h1>
                                <ul class="list-unstyled mt-3 mb-4">
                                    <li>Upgrade Priority Status</li>
                                    <li>Ads Free </li>
                                    <li>Priority email support</li>
                                    <li>Help center access</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="col">
                        <div class="card mb-4 rounded-3 shadow-sm border-primary">
                            <div class="card-header py-3 text-bg-primary border-primary">
                                <h4 class="my-0 fw-normal">Enterprise</h4>
                            </div>
                            <div class="card-body">
                                <h1 class="card-title pricing-card-title">$9<small class="text-muted fw-light">/mo</small></h1>
                                <ul class="list-unstyled mt-3 mb-4">
                                    <li>Highest Priority Status</li>
                                    <li>Ads Free </li>
                                    <li>Phone and email support</li>
                                    <li>Help center access</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="container py-5">
                    <hr class="featurette-divider"> <!-- divider of section -->
                    <br> 
                </div>
            </main>     
        </div>

        <!-- Section6 -->
        <div id="contact">
            <div class="bg-white py-5">
                <div class="container py-5">
                    <br>
                    <div class="row align-items-center mb-5">
                        <div class="col-lg-6 order-2 order-lg-1">
                            <h2 class="font-weight-light">We'd love to hear from you!</h2><br>
                            <section class="Material-contact-section section-padding section-dark">
                                <div class="container">

                                    <div class="row">
                                        <!-- Section Title -->

                                        <div class="col-md-6 wow animated fadeInRight" data-wow-delay=".2s">
                                            <form class="shake" role="form" method="post" id="contactForm" name="contact-form" data-toggle="validator">
                                                <!-- Name -->
                                                <div class="form-group label-floating">
                                                    <label class="control-label" for="name">Name</label>
                                                    <input class="form-control" id="name" type="text" name="name" required data-error="Please enter your name">
                                                    <div class="help-block with-errors"></div>
                                                </div><br>
                                                <!-- email -->
                                                <div class="form-group label-floating">
                                                    <label class="control-label" for="email">Email</label>
                                                    <input class="form-control" id="email" type="email" name="email" required data-error="Please enter your Email">
                                                    <div class="help-block with-errors"></div>
                                                </div><br>
                                                <!-- Subject -->
                                                <div class="form-group label-floating">
                                                    <label class="control-label">Subject</label>
                                                    <input class="form-control" id="msg_subject" type="text" name="subject" required data-error="Please enter your message subject">
                                                    <div class="help-block with-errors"></div>
                                                </div><br>
                                                <!-- Message -->
                                                <div class="form-group label-floating">
                                                    <label for="message" class="control-label">Message</label>
                                                    <textarea class="form-control" rows="3" id="message" name="message" required data-error="Write your message"></textarea>
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                                <!-- Form Submit -->
                                                <div class="form-submit mt-5">
                                                    <button class="btnSubmit" type="submit" id="form-submit"> Send Message</button>
                                                    <div id="msgSubmit" class="h3 text-center hidden"></div>
                                                    <div class="clearfix"></div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </section>

                        </div>
                        <div class="col-lg-5 px-5 mx-auto order-1 order-lg-2"><img src="https://bootstrapious.com/i/snippets/sn-about/img-1.jpg" alt="" class="img-fluid mb-4 mb-lg-0"></div>
                    </div>
                    <br>
                    <hr class="featurette-divider"> <!-- divider of section -->
                    <p class="float-left"><a href="#">Back to top</a></p>  
                </div>              
            </div>

        </div>
        <!-- FOOTER -->
        <footer class="footer-content">   
            <p>&copy; 2023 XYZ Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>       
        </footer>
    </body>
</html>
