package cn.dhbin.isme;

import cn.hutool.crypto.digest.BCrypt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BCryptTest {

	@Test
	void test() {
		Assertions.assertTrue(BCrypt.checkpw("123456", "$2a$10$FsAafxTTVVGXfIkJqvaiV.1vPfq4V9HW298McPldJgO829PR52a56"));
	}

}
