require 'test_helper'

class Asserts < TestCase
  def load_battle(file)
    begin
      YAML.load_file(@asserts_dir + "/" + file)
    rescue
      {invalid: true}
    end
  end

  def setup
    @asserts_dir = Dir.pwd + "/asserts"
    @asserts_files = Dir.entries(@asserts_dir) - ['.', '..']
  end

  def test_all_files_has_correct_syntax
    @asserts_files.each do |file|
      @battle = load_battle(file)

      assert { !@battle.has_key?(:invalid) }
    end
  end

  def test_top_level_key_allowed
    @allowed_keys = %w(name level tags description checks)

    @asserts_files.each do |file|
      @battle = load_battle(file)

      skip ("Incorrect file with assertions") if @battle.has_key?(:invalid)

      @battle.keys.each do |key|
        assert { @allowed_keys.include?(key) }
      end
    end
  end

  def test_exercise_uniq
    @battles = []
    @asserts_files.each do |file|
      @battle = load_battle(file)

      skip ("Incorrect file with assertions") if @battle.has_key?(:invalid)

      assert { !@battles.include?(@battle["name"]) }

      @battles << @battle["name"]
    end
  end
end
