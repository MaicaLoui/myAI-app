<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="{{ asset('css/app.css') }}" rel="stylesheet">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script
            src="https://code.jquery.com/jquery-3.2.1.js"
            integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
            crossorigin="anonymous">
    </script>
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
    <div class="d-flex p-2 bd-highlight">
        <div class="d-flex justify-content-start">
            <a class="navbar-brand" href="http://localhost:8000">
            <img src="{{ asset('img/Ictual.svg') }}" width="250" height="50" alt="">
            </a>
        </div>
        <div class="d-flex justify-content-end">
            <div id="img"></div>
        </div>
        <a class="navbar-brand" href="http://localhost:8000"></a>
    </div>
</nav>
<div class="container border-left border-secondary rounded mt-5 ">

    <div class="card-body ">
        <h4 id="exception"></h4>

        <div class="row">
            <div class="column">
                <h5 id="greeting">Say <b>Hello</b> if you need me!</h5>
                <!--when the keyword "hello" is said the text [hi, what can i help you with] is assigned to greeting to be display-->

                <br>
                <!--<img id="playbutton1"  value="PLAY"  onclick="play1()">
                <audio id="audio1" src="" ></audio>-->

            </div>
        </div>

        <form id="submit" method="POST" action="/">
            {{ csrf_field() }}

            <div class="row">
                <div id="trans" class="col-sm-offset-3 col-sm-6 "></div>
                <!--the speech-to-text and text-to-speech are assigned to the id trans for display-->
            </div>

            <div id="myDiv" style="display:none">
                <img id="myImage" src="{{ asset('img/typing.gif') }}">
            </div>
            <br>
            <div id="subButton" class="col-sm-offset-3 col-sm-6 "></div>
        </form>

        <div id="subButton" class="col-sm-offset-3 col-sm-6 "></div>
        <div id="submitting" style="display:none">
            <div class="d-flex justify-content-center">
                <img id="myImage" width="100" height="100" src="{{ asset('img/load.svg') }}">
            </div>
        </div>

        <img id="playbutton" value="PLAY" onclick="play2()">
        <audio id="audio2" src=""></audio>

        <!--button is for test purposes, can be removed-->
<!--        <input type="submit" id="some text" class="btn btn-primary" onclick="addUpdateData(id)" value="Add"></button>
-->        <!--button is for test purposes, remove when done-->

    </div>

    <script type="text/javascript" src="{{ asset('js/SpeechRecognition.js') }}"></script>

</body>
</html>
