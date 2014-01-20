#TODO extract to separate gem
module BattleAsserts
  module SyntaxChecker
    def self.ruby(expression, &block)
      f = IO.popen("ruby -c -e '#{unescape(expression)}'", :err=>[:child, :out])
      yield f.read
      f.close
    end
  end
end

#FIXME extract
def unescape(string)
  dup = string.dup
  escaped_chars = ['n', 't', 'r', 'f', 'v', '0', 'a']
  escaped_subs = {
    "n" => "\n",
    "t" => "\t",
    "r" => "\r",
    "f" => "\f",
    "v" => "\v",
    "0" => "\0",
    "a" => "\a"
  }

  pos = 0
  while pos < dup.length
    if dup[pos] == '\\' and escaped_chars.include? dup[pos + 1]
      dup[pos..(pos + 1)] = escaped_subs[dup[pos + 1]]
    end
    pos += 1
  end

  return dup
end
