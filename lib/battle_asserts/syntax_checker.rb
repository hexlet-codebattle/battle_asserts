#TODO extract to separate gem
module BattleAsserts
  module SyntaxChecker
    def self.ruby(expression, &block)
      f = IO.popen("ruby -c -e \"#{expression.gsub('"', '\"')}\"", :err=>[:child, :out])
      yield f.read
      f.close
    end

    def self.javascript(expression)
      path = "/var/tmp/check_js_code.js"
      File.open(path, 'w') do |file|
        file.write expression
      end
      f = IO.popen("esvalidate /var/tmp/check_js_code.js", :err=>[:child, :out])
      yield f.read
      f.close
      FileUtils.rm(path)
    end
  end
end
