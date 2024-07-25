<!doctype html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- CSRF Token -->
    <meta name="csrf-token" content="{{ csrf_token() }}">

    <title>{{ config('app.name', 'Laravel') }}</title>

    <!-- Scripts -->
    <script src="{{ asset('js/app.js') }}" defer></script>
    <script src="{{ asset('js/application.js') }}" defer></script>

    <!-- Fonts -->
    <link rel="dns-prefetch" href="//fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet">

    <!-- Styles -->
    <link href="{{ asset('css/app.css') }}" rel="stylesheet">
</head>
<body>
<div class="card-body">
    <div class="row">
        <div class="col py-2 text-right">
            <p>Velden gemarkeerd met <span class="text-red">*</span> zijn verplicht.</p>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <!-- <h4 class="card-title text-orange">Card buyer</h4> -->

                    <div class="form-row">
                        <div class="form-group col-md-3">
<!--                            <label>Geslacht (M/V)*</label>
-->                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="changes[]" value="IDwijziging" required
                                       {{ (is_array(old('changes')) and in_array('IDwijziging', old('changes'))) ? ' checked' : '' }}>
                                <label class="form-check-label">ID wijziging</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="changes[]" value="Naamcorrectie"
                                       {{ (is_array(old('changes')) and in_array('Naamcorrectie', old('changes'))) ? ' checked' : '' }}>
                                <label class="form-check-label">Naamcorrectie</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="changes[]" value="Artswisseling"
                                       {{ (is_array(old('changes')) and in_array('Artswisseling', old('changes'))) ? ' checked' : '' }}>
                                <label class="form-check-label">Artswisseling</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="changes[]" value="Adreswijziging"
                                       {{ (is_array(old('changes')) and in_array('Adreswijziging', old('changes'))) ? ' checked' : '' }}>
                                <label class="form-check-label">Adreswijziging</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="changes[]" value="Telefoonwijziging"
                                       {{ (is_array(old('changes')) and in_array('Telefoonwijziging', old('changes'))) ? ' checked' : '' }}>
                                <label class="form-check-label">Telefoonwijziging</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="changes[]" value="Burgelijkestaatwijziging"
                                       {{ (is_array(old('changes')) and in_array('Burgelijkestaatwijziging', old('changes'))) ? ' checked' : '' }}>
                                <label class="form-check-label">Burgelijkestaatwijziging</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="changes[]" value="Klassewijziging"
                                       {{ (is_array(old('changes')) and in_array('Klassewijziging', old('changes'))) ? ' checked' : '' }}>
                                <label class="form-check-label">Klassewijziging</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="changes[]" value="Nationaliteitwijziging"
                                       {{ (is_array(old('changes')) and in_array('Nationaliteitwijziging', old('changes'))) ? ' checked' : '' }}>
                                <label class="form-check-label">Nationaliteitwijziging</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="changes[]" value="Apotheekwijziging"
                                       {{ (is_array(old('changes')) and in_array('Apotheekwijziging', old('changes'))) ? ' checked' : '' }}>
                                <label class="form-check-label">Apotheekwijziging</label>
                            </div>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col">
                            <label for="ep_idnumber">ID nummer<span class="text-red">*</span></label>
                            <input type="number" name="ep_idnumber" id="ep_idnumber" class="form-control" data-validation="required">
                        </div>
                        <div class="form-group col">
                            <label for="ep_birthdate">Geboortedatum<span class="text-red">*</span></label>
                            <input type="date" name="ep_birthdate" id="ep_birthdate" class="form-control datepicker2">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col">
                            <label for="ep_surname">Achternaam<span class="text-red">*</span></label>
                            <input type="text" name="ep_surname" id="ep_surname" class="form-control" data-validation="required">
                        </div>
                        <div class="form-group col">
                            <label for="ep_firstname">Voornamen<span class="text-red">*</span></label>
                            <input type="text" name="ep_firstname" id="ep_firstname" class="form-control" data-validation="required">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col">
                            <label for="ep_civil_status">Burgelijke Staat<span class="text-red">*</span></label>
                            <select name="ep_civil_status" id="ep_civil_status" class="form-control" required>
                                <option value="" disabled selected>Select one</option>
                                <option value="Gehuwd">Gehuwd</option>
                                <option value="Gescheiden">Gescheiden</option>
                                <option value="Ongehuwd">Ongehuwd</option>
                                <option value="Verweduwd">Verweduwd</option>
                            </select>
                        </div>
                        <div class="form-group col">
                            <label for="ep_marriedname">Gehuwde naam</label>
                            <input type="text" name="ep_marriedname" id="ep_marriedname" class="form-control" data-validation="required">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col">
                            <label for="ep_gender">Geslacht<span class="text-red">*</span></label>
                            <select name="ep_gender" id="ep_gender" class="form-control" required>
                                <option value="" disabled selected>Select one</option>
                                <option value="Man">Man</option>
                                <option value="Vrouw">Vrouw</option>
                            </select>
                        </div>
                        <div class="form-group col">
                            <label for="ep_nationality">Nationaliteit<span class="text-red">*</span></label>
                            <select name="ep_nationality" id="ep_nationality" class="form-control" required>
                                <option value="" disabled selected>Select one</option>
                                <option value="Amerikaans">Amerikaans</option>
                                <option value="Nederlands">Nederlands</option>
                                <option value="Spaans">Spaans</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col">
                            <label for="ep_address">Adres<span class="text-red">*</span></label>
                            <input type="text" name="ep_address" id="ep_address" class="form-control" data-validation="required">
                        </div>
                        <div class="form-group col">
                            <label for="ep_phonenumber">Telefoon<span class="text-red">*</span></label>
                            <input type="number" name="ep_phonenumber" id="ep_phonenumber" class="form-control" data-validation="required">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col">
                            <label for="ep_general_practitioner">Huisarts<span class="text-red">*</span></label>
                            <input type="text" name="ep_general_practitioner" id="ep_general_practitioner" class="form-control" data-validation="required">
                        </div>
                        <div class="form-group col">
                            <label for="ep_pharmacy">Apotheek<span class="text-red">*</span></label>
                            <input type="text" name="ep_pharmacy" id="ep_pharmacy" class="form-control" data-validation="required">
                        </div>
                    </div>
                </div>
                <div class="card-footer text-right">
                    <div class="d-flex">
                        <div class="btn-group ml-auto">
                            <button type="submit" class="btn btn-primary my-1">Submit</button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</body>