package com.mibanma.api;

import java.io.File;
//import java.awt.PageAttributes.MediaType;
//import org.springframework.http.MediaType;
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.voicerss.tts.*;
import com.mibanma.entities.Alertetext;
import com.mibanma.entities.Motexpressionmr;
import com.mibanma.impl.AlertetextImpl;

//import com.google.cloud.texttospeech.v1.AudioConfig;
//import com.google.cloud.texttospeech.v1.AudioEncoding;
//import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
//import com.google.cloud.texttospeech.v1.SynthesisInput;
//import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
//import com.google.cloud.texttospeech.v1.TextToSpeechClient;
//import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
//import com.google.common.io.Files;
//import com.google.protobuf.ByteString;
//import com.google.rpc.context.AttributeContext.Resource;

//import com.mibanma.impl.MotexpressionfrImplold;
import com.mibanma.impl.MotexpressionmrImpl;
//import com.mibanma.api.SecurityConstants;
import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import jakarta.annotation.PostConstruct;
import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.Player;

@RestController
@CrossOrigin()
@RequestMapping("api/")
public class Traducteur {
	@Autowired
	MotexpressionmrImpl impl;
	@Autowired 
	AlertetextImpl alerteImpl;
	private String audioMp3;
	
	private static final String DIRECTORY = "src/main/resources/Audio/";

    @PostConstruct
    public void init() {
        File dir = new File(DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void saveFile(String fileName, byte[] data) throws IOException {
        File file = new File(DIRECTORY + fileName);
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(data);
            System.out.println("Audio content written to file " + fileName);
        }
    }
	/*
	 * fonction de tokenisation avec delimiteur explicite. cette fonction est à @Autowired
	MotexpressionmrImpl impl;
	@Autowired
	AlertetextImpl alerteImpl;
	 * appler si le delimieur est different de espace
	 */
	public List<String> getTokens(String str, String delimiter) {
		List<String> tokens = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(str, delimiter);
		while (tokenizer.hasMoreElements()) {
			tokens.add(tokenizer.nextToken());
		}
		return tokens;
	}

	/*
	 * fonction de tokenisation sans delimiteur explicite donc espace par defaut
	 */
	public List<String> getTokens(String str) {
		List<String> tokens = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(str);
		while (tokenizer.hasMoreElements()) {
			tokens.add(tokenizer.nextToken());
		}
		return tokens;
	}

	/*
	 * utilisation de l'API de google TTS pour la generation de l'audio du texte
	 * passé en paramètre.
	 */
	public String synthesizeText(String text) throws Exception {
		// Instantiates a client
		String audioname = "Fait";

		VoiceProvider tts = new VoiceProvider("820c62af0f4048ec80d5e15d53b388d3");

		VoiceParameters params = new VoiceParameters(text, Languages.French_France);
		params.setCodec(AudioCodec.WAV);
		params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
		params.setBase64(false);
		params.setSSML(false);
		params.setRate(0);

		byte[] voice = tts.speech(params);

//        FileOutputStream fos = new FileOutputStream("voice.mp3");
//        fos.write(voice, 0, voice.length);
//        fos.flush();
//        fos.close();
		// Write the response to the output file.
		audioname = "audio_" + Math.random() + ".mp3";
		saveFile(audioname, voice);
//		try (OutputStream out = new FileOutputStream(SecurityConstants.urlAudio + audioname)) {
//			audioMp3 = audioname;
//			audioname = SecurityConstants.urlAudio + audioname;
//			// Enregistrement du fichier audio à l'emplacement prévu sur le disque
//			out.write(voice);
//			System.out.println("Audio content written to file " + audioname);
//		}
//		try (OutputStream out = new FileOutputStream(SecurityConstants.urlAudio + audioname)) {
//			audioMp3 = audioname;
//			audioname = SecurityConstants.urlAudio + audioname;
//			// Enregistrement du fichier audio à l'emplacement prévu sur le disque
//			out.write(voice);
//			System.out.println("Audio content written to file " + audioname);
//		}

		return audioname;
	}
	
	public byte[] synthesizeTextAudio(String text) throws Exception {

		VoiceProvider tts = new VoiceProvider("780ff646d961460084b7259094169aee");

		VoiceParameters params = new VoiceParameters(text, Languages.French_France);
		params.setCodec(AudioCodec.MP3);
		params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
		params.setBase64(false);
		params.setSSML(false);
		params.setRate(0);

		byte[] voice = tts.speech(params);		

		return voice;
	}
	
	/*
	 * cette fonction permet de trouver la correspondance d'une expression en moore
	 */
	public String traduireMot(String motFR) {
		String motMr = null;
		if ("".equals(motFR)) {
			System.out.println("Aucune expression ou mot à traduire");
		} else {
			try {
				if (".".equals(motFR.substring(motFR.length() - 1, motFR.length()))
						|| ",".equals(motFR.substring(motFR.length() - 1, motFR.length()))
						|| ";".equals(motFR.substring(motFR.length() - 1, motFR.length()))
						|| "!".equals(motFR.substring(motFR.length() - 1, motFR.length()))
						|| "?".equals(motFR.substring(motFR.length() - 1, motFR.length()))) {
					String motfr = motFR.substring(0, motFR.length() - 1);
					motMr = impl.findByMotexpressionfr(motFR.substring(0, motFR.length() - 1))
							+ motFR.substring(motFR.length() - 1, motFR.length());
					/// System.err.println(motFR);
					/// System.err.println(motfr);
				} else {
					motMr = impl.findByMotexpressionfr(motFR);
				}
			} catch (Exception e) {
				e.getCause();
			}
		}
		return motMr;
	}

	/*
	 * cette fonction permet de rechercher un texte entier en mooré à partir de sa
	 * correspondance en français
	 */
	public String traduireFullText(String fulltxtFr) {
		String fulltxtMr = null;
		if ("".equals(fulltxtFr)) {
			System.out.println("Aucune expression ou mot à traduire");
		} else {

			try {
				fulltxtMr = impl.findByMotexpressionfr(fulltxtFr);
				// System.out.println(fulltxtMr);
			} catch (Exception e) {
				e.getCause();
			}
		}
		return fulltxtMr;
	}

	/*
	 * cette fonction permet de reconstruire la phrase en moore
	 */
	public String constructeurMr(List<String> motsMr) {
		String phraseMr = "";
		for (int i = 0; i < motsMr.size(); i++) {
			phraseMr = phraseMr + motsMr.get(i) + " ";
		}
		return phraseMr;
	}

	/*
	 * cette fonction permet d'enregistrer une alerte :
	 */
	public void saveAlert(String expression) {
		Alertetext alerte = new Alertetext();
		alerte.setDatetxt(new Date());
		alerte.setMotexp(expression);
		alerteImpl.create(alerte);
	}

	/**
	 *
	 * api de la plateforme
	 */
	/*
	 * cet ANCIEN api permet de tokeniser une phrase recu en paramettre de traduire
	 * mot par mot et en fin de construire le texte en mooré
	 */
	@PostMapping("/traduiretextOld")
	private ResponseEntity<Traduire> traducteur(@RequestBody String textFr) {
		String textMr = null;
		Traduire traduire = new Traduire();
		List<String> motsFr = new ArrayList<>();
		List<String> motsMr = new ArrayList<>();
		System.out.println(motsFr);
		try {
			if (textFr == null) {
			} else {
				if ("" != traduireFullText(textFr)) {
					textMr = traduireFullText(textFr);
				} else {

					// mis en commentaire le 08/06/2024 pour tester traduireFullText uniquement
					motsFr = getTokens(textFr);
					if (!motsFr.isEmpty()) {
						int i = 0;
						do {
							String motMr = traduireMot(motsFr.get(i)); // appel de la fonction de recherche de
																		// correspondance
							if (motMr != null) {
								motsMr.add(motMr);
							} else {
								saveAlert(motsFr.get(i));
							}
							i++;
						} while (i < motsFr.size());
					}
					textMr = constructeurMr(motsMr);

				}
			}
			traduire.setTextFr(textFr);
			traduire.setTextLangue(textMr);
			traduire.setLangue("Mooré");
			return new ResponseEntity<>(traduire, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	// Ajoutée pour test 10/06/2024
	// ---------src/main/resources/audios/audio_0.024998012436470263.mp3
	@PostMapping("/traduiretext")
    private ResponseEntity<Traduire> traducteurFullText(@RequestBody String textFr) throws FileNotFoundException, JavaLayerException {
        String textMr = null;
        Traduire traduire = new Traduire();
        List<String> motsFr = new ArrayList<>();
        List<String> motsMr = new ArrayList<>();
//        List<String> motsPhraseFr = new ArrayList<>();
//        String phraseFr = "";
//        String phraseMr = ""; 
        String nomaudio = "";
        byte[] audioContent;
        //String audioname;
//        audioname = "audio_" + Math.random() + ".mp3";
        //System.out.println(motsFr);
        try 
        {
            if (textFr == null) 
            {
            } 
            else 
            {
            	//On evalue si le texte entier existe dans la base de données -- null!=impl.findByUrlaudio(textFr) && impl.findByUrlaudio(textFr)
            	
            	if(null!=traduireFullText(textFr))
            	{
            		//textMr = traduireFullText(textFr);

               		//nomaudio = synthesizeText(textMr);//Mis en pause pour raison facturation le 13/08/2024. A réactiver dès que mise en prod Rise Up
               		            		
               		  int idmemr = impl.findIdmemr(textFr);
               		  Motexpressionmr mr = new Motexpressionmr();
               		  mr = impl.find(idmemr).get();
               		  textMr = mr.getFormecritmr();
               		  nomaudio = mr.getUrlaudio();
               		//System.out.println(nomaudio);
               		  if(nomaudio==null)
               		  {
               			audioContent = synthesizeTextAudio(textMr);
                   		nomaudio = "audio_" + Math.random() + ".mp3";
                   		mr.setUrlaudio(nomaudio);
                   		mr.setAudiocontent(audioContent);
                   		impl.update(mr);
               		  }

//            		//Lecture de l'audio
//            		Player player;            		            		
//            		FileInputStream fis;            		
//            		fis = new FileInputStream(nomaudio); 
//            		System.out.println("fait");
//            		BufferedInputStream bis = new BufferedInputStream(fis);
//            		player = new Player(bis);
//            		player.play();
//            		   //Fin        		
            		
            		  
            		Audio audio = new Audio();                    
                    audio.setNomaudio(nomaudio);
                    audio.setTextMR(textMr);            
                    
            	}            	
            	//si le texte entier n'existe pas dans la bd, on fait la traduction mot par mot. Si un mot fr n'a pas sa traduction dans la bd, on l'ajoute comme tel
            	else
            	{
            		//On découpe en tokens
            		 motsFr = getTokens(textFr);            		 
            		 //System.out.println("fait");
 					if (!motsFr.isEmpty()) 
 					{
 						int i = 0;
 						do {
 							String motMr = traduireMot(motsFr.get(i)); // appel de la fonction de recherche de
 																		// correspondance
 							if (motMr != null) 
 							{
 								motsMr.add(motMr);
 							} 
 							else 
 							{
 								motsMr.add(motsFr.get(i));
 								saveAlert(motsFr.get(i));
 							}
 							//System.out.println("fait Do");
 							i++;
 						} while (i < motsFr.size());
 					}
 					textMr = constructeurMr(motsMr);
 					nomaudio = "audio_" + Math.random() + ".mp3";
 					audioContent = synthesizeTextAudio(textMr);
 					//System.out.println("fait Do");
// 					nomaudio = synthesizeText(textMr);
            		             		 
            		 
            	/*else
            	{
            		//On découpe en tokens
            		 motsFr = getTokens(textFr);
            		  //System.out.println(motsFr);
					  if (!motsFr.isEmpty()) 
					  { 
						int i = 0; 
					  	do {
					  		//On verifie si nous avons un délimiteur de phrase
					  		if (".".equals(motsFr.get(i))
		                        || ":".equals(motsFr.get(i))		                        
		                        || "!".equals(motsFr.get(i))
		                        || "?".equals(motsFr.get(i)))
		                    {					  			
					  			//Si oui, on fait la traduction de la phrase		                        
		                        if(null!=traduireFullText(phraseFr))
		                    	{
		                    		textMr += " "+traduireFullText(phraseFr);
		                    		phraseFr = "";
		                    	}
		                        else
		                        {
		                        	int y = 0;
		                        	motsPhraseFr = getTokens(phraseFr);
		                        	////System.out.println(phraseFr);
		                        	do {
		                        		String motMr = traduireMot(motsPhraseFr.get(y)); // traduire chaque mot de la phrase n'ayant pas été traduite globalement 
							  			if (motMr != null) 
							  			{ 
							  				phraseMr += " "+motMr;
							  				//motsMr.add(motMr); 
							  			} 
							  			else 
							  			{
							  				phraseMr += " "+motsPhraseFr.get(y);
							  				System.out.println(phraseMr);
							  				//motsMr.add(motsPhraseFr.get(y));
							  				saveAlert(motsFr.get(y)); 
							  			} 
							  			
							  			y++;
		                        	}
		                        	while(y < motsPhraseFr.size());
		                        	//textMr += " "+motsMr;
		                        	//motsMr.clear(); 
		                        	textMr += " "+phraseMr;
		                        }		                        	
		                    }
					  		else //sinon, on concatène le mot à la phrase en cours
					  			{phraseFr += motsFr.get(i)+" "; 
					  			
					  			System.out.println(phraseFr);}
					  		i++; 
					  	} 
					  	while (i < motsFr.size()); 
					  } */
					  //textMr = constructeurMr(motsMr);					 
            	}            	      
            }
          
//    		 //Lecture de l'audio
//               try (FileInputStream fis = new FileInputStream(nomaudio)) {
//       			Player player;
//       			//System.out.println("fait");
//           		BufferedInputStream bis = new BufferedInputStream(fis);
//           		player = new Player(bis);
//           		player.play();
//       		} catch (IOException e) {
//       		    e.printStackTrace();
//       		}
//               //Fin lecture
            traduire.setTextFr(textFr);
            traduire.setTextLangue(textMr);
            traduire.setLangue("Mooré");
            //traduire.setAudioUrl("http://83.147.38.176:8080/api/lireaudio/"+nomaudio.substring(7));
            //traduire.setAudioUrl("http://83.147.38.168:8080/mibanma/api/lireaudio/"+nomaudio.substring(6));
            //traduire.setAudioUrl("http://localhost:8080/api/lireaudio/"+nomaudio);
            traduire.setAudioUrl("http://83.147.38.176:8080/mibanma/api/lireaudio/"+nomaudio);
            return new ResponseEntity<>(traduire, HttpStatus.OK);
        }
        catch(Exception e)
        {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
	}

	/*
	 * cet API permet de generer l'audio d'un texte passé en paramètre.
	 */
//    @PostMapping("genereraudio")
//    private ResponseEntity<String> textToSpeech(@RequestBody String textMr) throws Exception {
//        String nomaudio = synthesizeText(textMr);
//        return new ResponseEntity<>(nomaudio, HttpStatus.OK);
//    }
	@PostMapping("/genereraudio")
	private ResponseEntity<Audio> textToSpeech(@RequestBody String textMr) throws Exception {
		Audio audio = new Audio();
		String nomaudio = synthesizeText(textMr);
		audio.setNomaudio(nomaudio);
		audio.setTextMR(textMr);
		return new ResponseEntity<>(audio, HttpStatus.OK);
	}

	/*
	 * cet API permet de lire un fichier aufion dont le nom est passé en paramètre
	 */
//  @GetMapping("lireaudio/{audio}")
//  private ResponseEntity<byte[]> lireAudio(@PathVariable("audio") String audio) throws FileNotFoundException, JavaLayerException {
//
//      try {
//          byte[] audioencour = null;
//          Path aud = Paths.get(SecurityConstants.urlAudio + audio);
//          audioencour = Files.readAllBytes(aud);
//          return new ResponseEntity<>(audioencour, HttpStatus.OK);
//      } catch (Exception e) {
//          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//      }
//
//  }
//    @GetMapping("lireaudio/{audio}")
//    public ResponseEntity<Resource> lireAudio(@PathVariable("audio") String audio) throws FileNotFoundException {
//        try {
//            Path aud = Paths.get(SecurityConstants.urlAudio + audio);
//            InputStreamResource audioResource = new InputStreamResource(new FileInputStream(aud.toFile()));
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
////            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
//            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(audio).build());
//
//            return new ResponseEntity<Resource>(headers, HttpStatus.OK);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }

	@GetMapping("lireaudio/{audio}")
	private ResponseEntity<byte[]> lireAudio(@PathVariable("audio") String audio) throws IOException {
		//mr = new Motexpressionmr();
		Motexpressionmr mr = new Motexpressionmr();
		mr = impl.findByAudio(audio);
		byte[] audioData = null;
		//Path audioPath = Paths.get(DIRECTORY + audio);
		//audioData = Files.readAllBytes(audioPath);
		audioData = mr.getAudiocontent();
		return new ResponseEntity<>(audioData, HttpStatus.OK);
	}
	
//	@GetMapping("lireaudio/{audio}")
//	private ResponseEntity<byte[]> lireAudio(@PathVariable("audio") String audio) throws IOException {
//		try {
//			byte[] audioData = null;
//			Path audioPath = Paths.get(DIRECTORY + audio);
//			audioData = Files.readAllBytes(audioPath);
//			return new ResponseEntity<>(audioData, HttpStatus.OK);
//		} catch (IOException e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		}
//	}
}
