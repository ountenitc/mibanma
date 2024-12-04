package com.mibanma.api;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.mibanma.entities.Motexpressionmr;
import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

public class Example {
//	public static void main (String args[]) throws Exception {
//		String audioname = "Fait";
//		String audioMp3;
//		VoiceProvider tts = new VoiceProvider("820c62af0f4048ec80d5e15d53b388d3");
//		
//        VoiceParameters params = new VoiceParameters("mam dabda raaga", Languages.French_France);
//        params.setCodec(AudioCodec.WAV);
//        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
//        params.setBase64(false);
//        params.setSSML(false);
//        params.setRate(0);
//		
//        byte[] voice = tts.speech(params);
//		
////        FileOutputStream fos = new FileOutputStream("voice.mp3");
////        fos.write(voice, 0, voice.length);
////        fos.flush();
////        fos.close();
//      //Write the response to the output file.
//        audioname = "audio_" + Math.random() + ".mp3";
//	    try (OutputStream out = new FileOutputStream(SecurityConstants.urlAudio + audioname)) {
//	       	audioMp3 = audioname;
//	      	audioname = SecurityConstants.urlAudio + audioname;
//	      	//Enregistrement du fichier audio à l'emplacement prévu sur le disque
//	        out.write(voice);
//	        System.out.println("Audio content written to file " + audioname);	          
//	      }
//    }

//Code Google TTS synthetiseur()
//  try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
//  // Set the text input to be synthesized
//  SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();
//  ///System.err.println("Synthetiseur input " + input.getText());
//  // Build the voice request
//  VoiceSelectionParams voice
//          = VoiceSelectionParams.newBuilder()
//                  .setLanguageCode("fr-FR") // languageCode = "en_us"
//                  .setSsmlGender(SsmlVoiceGender.MALE) // ssmlVoiceGender = SsmlVoiceGender.FEMALE
//                  .build();
//
//  // Select the type of audio file you want returned
//  AudioConfig audioConfig
//          = AudioConfig.newBuilder()
//                  .setAudioEncoding(AudioEncoding.MP3) // MP3 audio.
//                  .build();
//
//  // Perform the text-to-speech request
//  SynthesizeSpeechResponse response
//          = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
//
//  // Get the audio contents from the response
//  ByteString audioContents = response.getAudioContent();
//
//  // Write the response to the output file.
//  audioname = "audio_" + Math.random() + ".mp3";
//  try (OutputStream out = new FileOutputStream(SecurityConstants.urlAudio + audioname)) {
//  //try (OutputStream out = new FileOutputStream(audioname)) {
//  	audioMp3 = audioname;
//  	audioname = SecurityConstants.urlAudio + audioname;
//  	//Enregistrement du fichier audio à l'emplacement prévu sur le disque
//      out.write(audioContents.toByteArray());
//      System.out.println("Audio content written to file " + audioname);
//      // return audioContents;
//  }
//}
	
	
//	if(null!=traduireFullText(textFr))
//	{    
//		textMr = traduireFullText(textFr);
//		int idmemr = impl.findIdmemr(textFr);        		
//		Motexpressionmr mr = new Motexpressionmr();
// 		  mr = impl.find(idmemr).get();
// 		 nomaudio = mr.getUrlaudio();
//		//nomaudio = impl.findByUrlaudio(textMr);
//		System.out.println("fait traduire"+nomaudio);
//		if (nomaudio == null) 
//		{
////   		  nomaudio = impl.findByUrlaudio(textFr);   
////   		  System.out.println("fait traduire");
////   	 	}
////   	   	else
////   	   	{  
//   		  //nomaudio = synthesizeText(textMr);
//			
//   	   	audioname = "audio_" + Math.random() + ".mp3";
//   	   	nomaudio = audioname;
//   	   		audioContent = synthesizeTextAudio(textMr);
//   		  
//   		  
//   		  //mr = new Motexpressionmr();
//   		
//   		  mr.setUrlaudio(nomaudio);
//   		  mr.setAudiocontent(audioContent);
//   		  impl.update(mr);               		
//   	   	//}
//		}
//		   	Audio audio = new Audio();                     
//		   	audio.setNomaudio(nomaudio);
//		   	audio.setTextMR(textMr);                 
//	}
	
}
