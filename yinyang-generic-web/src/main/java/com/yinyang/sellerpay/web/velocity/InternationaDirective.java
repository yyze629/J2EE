package com.yinyang.sellerpay.web.velocity;

import com.yinyang.sellerpay.web.velocity.InternationalizationProperties;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;

public class InternationaDirective extends Directive
{
  private InternalContextAdapter contexts;

  public String getName()
  {
    return "sdh";
  }

  public int getType()
  {
    return 2;
  }

  public boolean render(InternalContextAdapter context, Writer writer, Node node)
    throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException
  {
    this.contexts = context;

    int count = node.jjtGetNumChildren();
    List list = new ArrayList();

    list = getNodeList(list, node, 0, --count);
    writer.write(getStr(list));
    return true;
  }

  private List<String> getNodeList(List<String> list, Node node, int countMin, int countMax)
  {
    SimpleNode sn_region = (SimpleNode)node.jjtGetChild(countMin);
    String region = (String)sn_region.value(this.contexts);
    if (region != null)
      list.add(InternationalizationProperties.getProperty(region));

    ++countMin;
    if (countMax > countMin)
      return getNodeList(list, node, countMin, countMax);

    return list;
  }

  private String getStr(List<String> list)
  {
    if (list.size() > 1) {
      String oneStr = (String)list.remove(0);
      return fillStringByArgs(oneStr, list);
    }
    return ((String)list.get(0));
  }

  private static String fillStringByArgs(String str, List<String> list)
  {
    Matcher m = Pattern.compile("\\{(\\d)\\}").matcher(str);
    int i = 0;
    while (m.find()) {
      if (i == list.size())
        return str;

      str = str.replace(m.group(), (CharSequence)list.get(i));
      ++i;
    }
    return str;
  }

  public static void main(String[] args) {
    List list = new ArrayList();
    list.add("中国人");
    list.add("北京");
    list.add("22");
    list.add("vv");
    list.add("xxx");

    String str = "我123是{0},我来自{1},今年{2}岁{3}";

    System.out.println(fillStringByArgs(str, list));
  }
}