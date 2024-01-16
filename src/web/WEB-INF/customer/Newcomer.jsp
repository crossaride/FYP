<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Create Profile</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
        <!-- Hyperlinks -->
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/newcomer.css">
        <script type = "text/javascript" src="<%= request.getContextPath()%>/javascript/newcomer.js"></script>  

    </head>

    <body>
        <div class="container">

            <div class="form-container">
                <div class="left-container">
                    <div class="left-inner-container">
                        <h2>Welcome!</h2>
                        <p>To get started... <br> Let's create a profile!</p>
                        <br><br><br><br><br><br><br><br>

                        <p>Don't worry! <br> Your info is safe with us!</p>
                    </div>
                </div>

                <div class="right-container">
                    <div class="right-inner-container">
                        <form action="<%= request.getContextPath() %>/NewcomerServlet" method="post" enctype="multipart/form-data">
                            <h2 class="lg-view">Create Profile</h2>
                            <fieldset>
                                <div class="grid-65">
                                    <span class="photo" id="imagePlaceholder" title="Upload your Avatar!" ></span>
                                    <img id="imagePreview" name="imagePreview" src="#" alt="Preview" title="image preview">
                                </div>
                                <input type="file" id="avatar" name="avatar" accept="image/* " onchange="previewImage(); hideImage();"/>
                            </fieldset>
                            <div data-tip="This will become your login username">
                                <input type="text" name="username" placeholder="Username *" required/>
                            </div>
                            <div data-tip="Your real name shown on delivery packages">
                                <input type="text" name="realname" placeholder="Real Name *"  required/>
                            </div> 
                            <div data-tip="Example: 010-1234567">
                                <input type="phone" name="phone" placeholder="Phone number *"  required/>
                            </div>            
                            <input type="text" name="address" placeholder="Address *" required/>
                            <div class="errorMsg">
                                <span style="color:red;">${errorMessage}</span>
                            </div>
                            <button id="btnSubmit">Submit</button>
                            <input type="submit" id="submit" hidden></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html