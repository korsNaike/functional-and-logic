fun main() { val quain = "fun main() { val quain = \"...\"; val escQuain = quain.replace(\"\" + 0x22.toChar(), \"\" + 0x5c.toChar() + 0x22.toChar()); println(quain.replaceFirst(\"...\", escQuain)); }"; val escQuain = quain.replace("" + 0x22.toChar(), "" + 0x5c.toChar() + 0x22.toChar()); println(quain.replaceFirst("...", escQuain)); }