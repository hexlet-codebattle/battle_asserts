require 'spec_helper'

describe "Asserts" do
  before(:all) do
    @allowed_keys = %w(name level tags description checks)
    @battles = []
  end

  asserts_dir = Dir.pwd + "/asserts"
  asserts_files = Dir.entries(asserts_dir) - ['.', '..']

  asserts_files.each do |file|
    describe "battle #{file}" do
      before(:all) do
        @battle =
          begin
            YAML.load_file(asserts_dir + "/" + file)
          rescue
            {invalid: true}
          end
      end

      it "file valid?" do
        expect(@battle.has_key?(:invalid)).to be_false
      end

      it "all top level keys allowed" do
        return false if @battle["name"] == nil
        @battle.keys.each do |key|
          expect { @allowed_keys.include?(key) }.to be_true
        end
      end

      it "exercise uniq" do
        return false if @battle["name"] == nil
        expect(@battles.include?(@battle["name"])).to be_false
        @battles << @battle["name"]
      end
    end
  end
end
