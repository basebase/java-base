package rpc.hadoop.v1;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/16 11:40:47
 * @Description:
 */
public class RPCMain {
    private static final int PORT = 8888;

    public static void main(String[] args) throws IOException {

        Server server = RPC.getServer(new TestImpl(), PORT);
        server.start();

        InetSocketAddress addr = new InetSocketAddress(PORT);
        TestProtocol proxy =
                (TestProtocol)RPC.getProxy(TestProtocol.class, addr);

        proxy.ping();
        String stringResult = proxy.echo("foo");
        System.out.println("res: " + stringResult);
    }
}


interface TestProtocol {
    void ping() throws IOException;
    String echo(String value) throws IOException;
    String[] echo(String[] value) throws IOException;
    int add(int v1, int v2) throws IOException;
    int add(int[] values) throws IOException;
    int error() throws IOException;
    void testServerGet() throws IOException;
}

class TestImpl implements TestProtocol {

    public void ping() {}

    public String echo(String value) throws IOException { return value; }

    public String[] echo(String[] values) throws IOException { return values; }

    public int add(int v1, int v2) {
        return v1 + v2;
    }

    public int add(int[] values) {
        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        return sum;
    }

    public int error() throws IOException {
        throw new IOException("bobo");
    }

    public void testServerGet() throws IOException {
        if (!(Server.get() instanceof RPC.Server)) {
            throw new IOException("Server.get() failed");
        }
    }

}
