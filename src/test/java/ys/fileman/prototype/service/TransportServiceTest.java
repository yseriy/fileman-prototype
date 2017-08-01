package ys.fileman.prototype.service;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ys.fileman.prototype.domen.Credentials;
import ys.fileman.prototype.domen.ModelFactory;
import ys.fileman.prototype.domen.Transport;
import ys.fileman.prototype.domen.transport.FTPTransport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransportService.class)
public class TransportServiceTest {

    @MockBean
    private ModelFactory mockModelFactory;
    @Autowired
    private TransportService transportService;

    @Test
    public void getFTPTransport() throws Exception {
        String server = "test_server";
        String login = "test_login";
        String password = "test_password";

        Credentials credentials = new Credentials(server, login, password);

        FTPClient mockFtpClient = mock(FTPClient.class);
        when(mockFtpClient.getReplyCode()).thenReturn(200);
        InOrder inOrder = inOrder(mockFtpClient);

        FTPTransport ftpTransport = new FTPTransport(mockFtpClient);
        when(mockModelFactory.getFTPClient()).thenReturn(mockFtpClient);
        when(mockModelFactory.getFTPTransport(mockFtpClient)).thenReturn(ftpTransport);

        Transport transport = transportService.getFTPTransport(credentials);

        assertThat(transport).isNotNull();
        assertThat(transport).isEqualTo(ftpTransport);

        inOrder.verify(mockFtpClient).connect(server);
        inOrder.verify(mockFtpClient).login(login, password);
    }
}
