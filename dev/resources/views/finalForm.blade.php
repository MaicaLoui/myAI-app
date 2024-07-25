<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="{{ asset('css/app.css') }}" rel="stylesheet">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.js" integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
        crossorigin="anonymous"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/annyang/2.6.1/annyang.min.js"></script>
    <script>
        var assetBaseUrl = "{{ asset('') }}";
    </script>

    <meta name="csrf-token" content="{{ csrf_token() }}">

</head>

<body>
    <!--<div class="container">-->
    <nav class="navbar navbar-light bg-light" style=" border-bottom-style: solid;
  border-bottom-color: #45bad2;">
        <a class="navbar-brand" href="http://localhost:8000">
            <img src="{{ asset('img/aibot.svg') }}" width="250" height="50" alt="">

        </a>
    </nav>

    <div class="container mt-5 ">
        <div class="card w-75 mx-auto">
            <div class="card-header">
                Your {{ $form->formName }} form has been submitted!
            </div>
            <div class="card-body">
                <h4 id="exception"></h4>

                <!--  <h5 class="card-title">Special title treatment</h5>-->

                <form>
                    <div class="row form-group col-4">
                        <div class="col">
                            <label> Your Name</label>
                            <input type="text" class="form-control" placeholder="name@example.com"
                                value="{{ $user->person }}" readonly>
                        </div>
                    </div>

                    <div class="form-group col-sm-6">
                        <label> Your Address</label>
                        <input type="text" class="form-control" placeholder="name@example.com"
                            value="{{ $user->address }}" readonly>
                    </div>

                    <div class="row form-group col-12">
                        <div class="col">
                            <label> Your Birthplace</label>
                            <input type="text" class="form-control" placeholder="name@example.com"
                                value="{{ $user->location }}" readonly>
                        </div>
                        <div class="col">
                            <label>Your Date of Birth</label>
                            <input type="text" class="form-control" placeholder="name@example.com"
                                value="{{ $user->date }}" readonly>
                        </div>

                    </div>
                    <div class="form-group col-sm-4">
                        <label>Your Number</label>
                        <input type="number" class="form-control" placeholder="name@example.com"
                            value="{{ $user->number }}" readonly>

                    </div>
                    <br>
                    <div id="subButton" class="col-sm-offset-3 col-sm-6 "></div>
                </form>

            </div>
        </div>

        <div id="subButton" class="col-sm-offset-3 col-sm-6 "></div>
        <img id="playbutton" value="PLAY" onclick="play2()">
        <audio id="audio2" src=""></audio>

        <!--   <input type="submit" id="some text" class="btn btn-primary" onclick="addUpdateData(id)" value="Add"></button>-->
        <!--button is for test purposes, can be removed-->

    </div>


    <script type="text/javascript" src="{{ asset('js/SpeechRecognition.js') }}"></script>


</body>

</html>
