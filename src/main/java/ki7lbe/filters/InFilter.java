package ki7lbe.filters;

import org.netcrusher.core.filter.TransformFilter;

import java.nio.ByteBuffer;
/* https://github.com/NetCrusherOrg/netcrusher-java/blob/master/core/src/main/java/org/netcrusher/core/filter/TransformFilter.java */
public class InFilter implements TransformFilter {
    @Override
    public void transform(ByteBuffer byteBuffer) {
        System.out.println("inFilter");
        if (byteBuffer != null) System.out.println(byteBuffer.position());
    }
}
