package ys.fileman.prototype.domen;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FmUserIdTest {

    @Test
    public void compare() {
        String brand1 = "test_brand1";
        String contract1 = "test_contract1";
        String account1 = "test_account1";

        String brand2 = "test_brand2";
        String contract2 = "test_contract2";
        String account2 = "test_account2";

        FmUserId fmUserId1 = new FmUserId(brand1, contract1, account1);
        FmUserId fmUserId2 = new FmUserId(brand1, contract1, account1);
        FmUserId fmUserId3 = new FmUserId(brand2, contract2, account2);

        assertThat(fmUserId1.equals(fmUserId2)).isTrue();
        assertThat(fmUserId1.equals(fmUserId3)).isFalse();
    }
}
