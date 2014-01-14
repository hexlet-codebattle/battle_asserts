#TODO extract to separate gem
module BattleAsserts
  module SyntaxChecker
    def self.ruby(expression)
      #FIXME command("ruby -c -e '#{expression}'")
    end
  end
end
