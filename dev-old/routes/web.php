<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/','GuzzleController@getGuzzleRequest');
Route::post('/intent','GuzzleController@getIntent');
Route::post('/','TestController@store');
Route::post('/tts','GuzzleController@Getmary');


Route::get('/layout','TestController@layout');
Route::post('/layout','TestController@store');
Route::get('/layout/session','TestController@session');

Route::get('/layout2/{user}', 'TestController@test2');

Route::get('clear-cache', function (){
    session()->flush();
});

Route::get('/test', 'TestController@test');

/*Route::get('/dashboard', 'TestController@dashboard');*/