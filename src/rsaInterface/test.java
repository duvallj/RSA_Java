package rsaInterface;

import rsaCrypt.Decoder;
import rsaCrypt.Encoder;

public class test {

	public static void main(String[] args) {
		Decoder d = new Decoder();
		Encoder e = new Encoder();
		
		System.out.println(d.decode(e.encode("Wow, I can't believe this works!")));


	}

}
