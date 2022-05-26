<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="{{ asset('css/bootstrap.css') }}" rel="stylesheet">
    <link href="{{ asset('css/app.css') }}" rel="stylesheet">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script
        src="https://code.jquery.com/jquery-3.2.1.js"
        integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
        crossorigin="anonymous">
    </script>
    <script>
        var assetBaseUrl = "{{ asset('') }}";
    </script>

    <meta name="csrf-token" content="{{ csrf_token() }}">

</head>
<body>

<nav class="navbar navbar-light bg-light" style=" border-bottom-style: solid;
  border-bottom-color: #45bad2;">

    <a class="navbar-brand" href="#">
        <img src="{{ asset('img/Ictual.svg') }}" width="250" height="50" alt="">

    </a>
</nav>
<div class="container">
    <div class="container border-left rounded mt-5 cust-border-left ">


        <div class="card">
            <div class="card-body ">
                <form method="POST" action="/layout">
                    {{ csrf_field() }}
                    <div class="row">

                        <div id="myDiv" style="display:none">
                            <div class="d-flex justify-content-center">
                                <img id="myImage" src="{{ asset('img/typing.gif') }}">
                            </div>
                        </div>

                        <div id="trans" class="col-sm-offset-3 col-sm-6 "></div>

                    </div>




            </div>
        </div>
        <br>
        <textarea data-v-7d7651fe="" id="sentences" type="text" rows="25" class="form-control" style="border-width: 3px;"></textarea>


    </div>
    <script type="text/javascript" src="{{ asset('js/SpeechRecognition.js') }}"></script>

</div>

</body>
</html>
