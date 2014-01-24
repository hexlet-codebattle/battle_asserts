#TODO extract to separate gem
module BattleAsserts
  module SyntaxChecker
    def self.ruby(expression, &block)
      f = IO.popen("ruby -c -e \"#{expression.gsub('"', '\"')}\"", :err=>[:child, :out])
      yield f.read
      f.close
    end

    def self.javascript(expression, &block)
    end
  end
end
