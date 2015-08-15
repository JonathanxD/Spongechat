/**
 *  Spongechat — A new Powered Chat Systemimport org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;
This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
package me.kaward.spongepowered.spongechat;

import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;

public class CommandHandler
{

	protected CommandHandler()
	{
	}

	protected CommandExecutor SpongechatCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(CommandSource cs, CommandContext cc) throws CommandException
		{
			cs.sendMessage(Texts.of(TextColors.DARK_AQUA, "[Spongechat] ").builder().append(Texts.of("Available commands for Spongechat (You can click on command to get description and get how-use):")).build());
			return HelpCommandExecutor.execute(cs, cc);
		}
	};

	protected CommandExecutor HelpCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(CommandSource cs, CommandContext cc) throws CommandException
		{
			// TODO Auto-generated method stub
			return null;
		}
	};

	protected CommandExecutor CreateChannelCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(CommandSource cs, CommandContext cc) throws CommandException
		{
			// TODO Auto-generated method stub
			return null;
		}
	};

	protected CommandExecutor RemoveChannelCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(CommandSource cs, CommandContext cc) throws CommandException
		{
			// TODO Auto-generated method stub
			return null;
		}
	};

	protected CommandExecutor MutePlayerCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(CommandSource cs, CommandContext cc) throws CommandException
		{
			// TODO Auto-generated method stub
			return null;
		}
	};

	protected CommandExecutor UnmutePlayerCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(CommandSource cs, CommandContext cc) throws CommandException
		{
			// TODO Auto-generated method stub
			return null;
		}
	};

	protected CommandExecutor MuteChannelCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(CommandSource cs, CommandContext cc) throws CommandException
		{
			// TODO Auto-generated method stub
			return null;
		}
	};

	protected CommandExecutor UnmuteChannelCommandExecutor = new CommandExecutor()
	{
		@Override
		public CommandResult execute(CommandSource cs, CommandContext cc) throws CommandException
		{
			// TODO Auto-generated method stub
			return null;
		}
	};

}
