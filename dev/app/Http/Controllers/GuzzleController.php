<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use function GuzzleHttp\Psr7\try_fopen;


class GuzzleController extends Controller
{

    public function getGuzzleRequest()
    {

        $client = new \GuzzleHttp\Client(['stream' => true]);
        $request = $client->get('http://localhost:59125/process?INPUT_TYPE=TEXT&OUTPUT_TYPE=AUDIO&INPUT_TEXT=What%20can%20i%20help%20you%20with!%0A&VOICE_SELECTIONS=cmu-slt-hsmm%20en_US%20female%20hmm&AUDIO_OUT=WAVE_FILE&LOCALE=en_US&VOICE=cmu-slt-hsmm&AUDIO=WAVE_FILEFILE');
        $response = $request->getBody()->getMetadata('uri');
        session()->forget('sentences');
        return view('STT', compact(['response']));

    }

    public function getIntent(Request $request)
    {

        $data = $request->input('data'); // 'data' is the phrase captured by the Speech-To-Text
        $client = new \GuzzleHttp\Client(['stream' => true]);
        $devRestAPI = $client->get('http://localhost:8080/intent?sentence=' . urlencode($data))->getBody()->getMetadata('uri');// 'data' will be passed to the NLP API to detect the Intent and it's Entities


        $found = false; // variable found is set to false
        if ($request->session()->has('sentences')) { // see if the sentence is already in session

            foreach ($request->session()->get('sentences') as $sentence) { // if yes? loop into the sentences

                if ($sentence == $data) { // if sentence equals the new entered sentence, set found to true
                    $found = true;
                    break;
                }
            }

        } else {
            $request->session()->push('sentences', $data); // if sentence is not in session, push to session
        }

        if (!$found) {
            $request->session()->push('sentences', $data);

            $client2 = new \GuzzleHttp\Client((['stream' => true]));  // JSON will be returned with Intent and Entity
            $intent = json_decode($client2->get($devRestAPI)->getBody()->getContents(), true); // returns intent, and entity(key&value)

            $entitySize = count($intent['entities']); // the size of the entity will be count and assigned to the var 'entitySize'

            if ($intent['entities'] == null) { // no entities
                $entityKey = ""; // assign a empty String to the entity key, if not an error will appear
                $entityValue = ""; // assign a empty String to the entity value, if not an error will appear
                $intent = $intent['name'];
            } else if ($entitySize == 1) { // 1 entity
                $entityKey = $intent['entities'][0]['key'];  // returns key of key/value for example if key is location it will return location.
                $entityValue = $intent['entities'][0]['value']; // returns value of key/value
                $intent = $intent['name']; // returns the intent
            } else if ($entitySize > 1) { // more than 1 entity
                $entityKey = $intent['entities'][0]['key'];  // returns value of key/value for example if key is place it will return Aruba.
                $entity2 = $intent['entities'][1]['key'];
                $entityValue = $intent['entities'][0]['value'];
                $intent = $intent['name'];
            }


            $client3 = new \GuzzleHttp\Client(['stream' => true]);

            if ($entitySize == 0) { // no entities
                $getIntentAPI = $client3->get('http://localhost:9090/' . $intent . '?key=' . urlencode($entityKey) . '&value=' . urlencode($entityValue))->getBody()->getMetadata('uri');
            } else if ($entitySize == 1) { // 1 entity
                $getIntentAPI = $client3->get('http://localhost:9090/' . $intent . '?key=' . urlencode($entityKey) . '&value=' . urlencode($entityValue))->getBody()->getMetadata('uri');
            } else { // more than 1 entity
                $getIntentAPI = $client3->get('http://localhost:9090/' . $intent . '?key=' . urlencode($entityKey) . '&value=' . urlencode($entity2))->getBody()->getMetadata('uri');
            }

        } else {

            $client2 = new \GuzzleHttp\Client((['stream' => true]));  // JSON will be returned with Intent and Entity
            $intent = json_decode($client2->get($devRestAPI)->getBody()->getContents(), true); // returns intent, and entity(key&value)

            $entitySize = count($intent['entities']); // the size of the entity will be count and assigned to the var 'entitySize'

            if ($intent['entities'] == null) { // no entities
                $entityKey = ""; // assign a empty String to the entity key, if not an error will appear
                $entityValue = ""; // assign a empty String to the entity value, if not an error will appear
                $intent = $intent['name'];
            } else if ($entitySize == 1) { // 1 entity
                $entityKey = $intent['entities'][0]['key'];  // returns key of key/value for example if key is location it will return location.
                $entityValue = $intent['entities'][0]['value']; // returns value of key/value
                $intent = $intent['name']; // returns the intent
            } else if ($entitySize > 1) { // more than 1 entity
                $entityKey = $intent['entities'][0]['key'];  // returns value of key/value for example if key is place it will return Aruba.
                $entity2 = $intent['entities'][1]['key'];
                $entityValue = $intent['entities'][0]['value'];
                $intent = $intent['name'];
            }

            $client3 = new \GuzzleHttp\Client(['stream' => true]);

                $getIntentAPI = $client3->get('http://localhost:9090/repeatIntent?key=' . urlencode($entityKey).'&value='. urlencode($entityValue))->getBody()->getMetadata('uri');

        }

        $client4 = new \GuzzleHttp\Client(['stream' => true]);
        // JSON of the result of getIntent API will be returned
        $answer = $client4->get($getIntentAPI)->getBody()->getContents();
        $voice = json_decode($answer);


        $client5 = new \GuzzleHttp\Client(['stream' => true]);
        // The var voice will be placed in the URI of maryTTS and this will return an audio
        $voiceURI = $client5->get('http://localhost:59125/process?INPUT_TYPE=TEXT&OUTPUT_TYPE=AUDIO&INPUT_TEXT=' . $voice->result . '&VOICE_SELECTIONS=cmu-slt-hsmm%20en_US%20female%20hmm&AUDIO_OUT=WAVE_FILE&LOCALE=en_US&VOICE=cmu-slt-hsmm&AUDIO=WAVE_FILEFILE');
        $ttsVoice = $voiceURI->getBody()->getMetadata('uri');

        return [json_decode($answer), $ttsVoice, $entityKey, $entityValue, $intent];

    }

    public function getMary(Request $request)
    {
        try {
            $data = $request->input('data');
            $client = new \GuzzleHttp\Client(['stream' => true]);
            $request = $client->get('http://localhost:59125/process?INPUT_TYPE=TEXT&OUTPUT_TYPE=AUDIO&INPUT_TEXT=' . $data . '&VOICE_SELECTIONS=cmu-slt-hsmm%20en_US%20female%20hmm&AUDIO_OUT=WAVE_FILE&LOCALE=en_US&VOICE=cmu-slt-hsmm&AUDIO=WAVE_FILEFILE');
            $response = $request->getBody()->getMetadata('uri');
            return $response;
        } catch (\Exception $e) {

            return $e->getMessage();
        }
    }

}
