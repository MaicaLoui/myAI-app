<?php


namespace App\Http\Controllers;


use App\Form;
use App\User;
use Illuminate\Http\Request;

class TestController
{

    public function test2($id)
    {


        $user = User::find($id);
        //  dd($id);
        session()->flush();
        return view('finalForm', compact('user'));

    }

    public function layout()
    {
        return view('layout');
    }

    public function session()
    {
        dd(request()->session()->all());
    }

    public function store(Request $request)
    {

        $user = new User();
        $form = new Form();

        $user->person = request('person');
        $user->address = request('address');
        $user->location = request('location');
        $user->date = request('date');
        $user->number = request('number');
        $user->save();

        $form->formType = request('formType');
        $form->formName = request('formName');
        $form->user()->associate($user);
        $form->save();

       $request->session()->flush();

        return view('finalForm', compact('user', 'form'));
    }

    public function test(){
        return view('test');
    }
}
